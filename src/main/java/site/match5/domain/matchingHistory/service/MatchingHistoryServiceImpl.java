package site.match5.domain.matchingHistory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.alarmEntity.entity.Alarm;
import site.match5.domain.alarmEntity.service.AlarmEntityService;
import site.match5.domain.client.dto.UpdateMoneyReq;
import site.match5.domain.client.service.ClientService;
import site.match5.domain.clientMatchingStatus.dto.ClientMatchingStatus;
import site.match5.domain.clientMatchingStatus.service.ClientMatchingStatusService;
import site.match5.domain.court.service.CourtService;
import site.match5.domain.courtSchedule.dto.CourtSchedule;
import site.match5.domain.courtSchedule.service.CourtScheduleService;
import site.match5.domain.matchedUser.dto.MatchedUser;
import site.match5.domain.matchedUser.service.MatchedUserService;

import site.match5.domain.matchingHistory.dto.MatchingHistoryRes;
import site.match5.domain.matchingHistory.dto.MatchingHistoryStatusReq;
import site.match5.domain.matchingHistory.repository.MatchingHistoryRepository;
import site.match5.domain.paymentHistory.dto.PaymentHistory;
import site.match5.domain.paymentHistory.service.PaymentService;
import site.match5.global.exception.customException.BusinessException;

import java.util.List;

import static site.match5.global.exception.errorCode.CommonErrorCode.NO_SUCH_ELEMENT;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchingHistoryServiceImpl implements MatchingHistoryService {
    private final MatchingHistoryRepository matchingHistoryRepository;
    private final ClientService clientService;
    private final MatchedUserService matchedUserService;
    private final PaymentService paymentService;
    private final AlarmEntityService alarmEntityService;
    private final CourtScheduleService courtScheduleService;
    private final ClientMatchingStatusService clientMatchingStatusService;

    /* 매칭 확정 비즈니스 로직
매칭 확정 버튼을 누를때 해당 매칭히스토리가있는건지 확인,
있으면 확정완료인원 증가시켜준다. 만약 증가전 값이 9라면 증가와 동시에
결제이력과 ,회원평가 데이터를 생성한다 + 모든 인원의 충전금액을 차감한다.
+ 알람데이터를 생성한다 ( 매칭 확정 ) + 매칭히스토리 상태를 변경한다.
 */
    @Transactional
    @Override
    public void confirmMatch(Integer matchingHistoryId, Integer userId) {

        // 매칭 존재 하는 매칭인지 검증
        if (matchingHistoryRepository.checkMatchingHistoryExist(matchingHistoryId).equals(0))
            throw new BusinessException(NO_SUCH_ELEMENT);

        // 매칭 확정 인원 증가
        matchingHistoryRepository.increaseConfirmUserCount(matchingHistoryId);
        log.info("확정버튼");
        // 유저 상태 => 매칭확정누름 상태로 변경
        clientMatchingStatusService.updateUserMatchingStatus(userId, ClientMatchingStatus.builder().status(2).build());
        Integer confirmUserCount = matchingHistoryRepository.findConfirmUserCount(matchingHistoryId);

        // 매칭 확정을 전부 다 눌렀을 경우  [ 총 인원은 10명 ]
        if ( confirmUserCount == 10) {
            log.info("확정 10명 {} ", confirmUserCount);
            // 해당 매칭에 소속된 유저들 반환
            List<MatchedUser> matchedUsers = matchedUserService.findMatchedUsers(matchingHistoryId);
            // 해당 매칭의 구장 스케줄 반환
            CourtSchedule courtSchedule = courtScheduleService.findScheduleByMatching(matchingHistoryId);


            // 구장 스케줄 가격
            int schedulePrice = courtSchedule.getProductPrice();
            // n빵
            int perPrice = schedulePrice / 10;

            // 결제 내역 생성 , 매칭 완료 알림 생성 , 해당 매칭에 소속된 유저들 구장 비용만큼 돈 차감
            for (MatchedUser matchedUser : matchedUsers) {
                paymentService.savePaymentHistory(new PaymentHistory(matchedUser.getUserId(), matchingHistoryId));
                alarmEntityService.saveAlarm(new Alarm(matchedUser.getUserId(), "매칭완료", matchingHistoryId));
                clientService.updateMoneyExp(matchedUser.getUserId(), new UpdateMoneyReq(perPrice));
                // 해당 매칭 사용자들 매칭완료 상태로 변경 및 경기 날짜로 등록
                clientMatchingStatusService.updateUserMatchingStatus(matchedUser.getUserId(), ClientMatchingStatus.builder().status(3).matchDay(courtSchedule.getScheduleDate()).matchTime(Integer.valueOf(courtSchedule.getStartT())).build());
            }

            // 매칭 히스토리를 결제완료,경기전 상태로 변경한다.
            updateMatchingHistoryStatus(matchingHistoryId, new MatchingHistoryStatusReq(1));
        }
    }

    /*
    매칭 취소 비즈니스 로직
    매칭 히스토리 아이디를 이용하여 해당 매칭이 존재하는지 확인한다.
    없다면 예외, 있다면 매칭 인원들에서 해당 매칭 인원들 삭제시켜주고 생성된 알림데이터에 삭제하고 난뒤
    매칭히스토리 삭제시켜준다. + 매칭취소 알림 데이터 생성 + 스케줄 예약을 다시 원래대로 수정
     */
    @Transactional
    @Override
    public void cancelMatch(Integer matchingHistoryId, Integer userId) {
        if (matchingHistoryRepository.checkMatchingHistoryExist(matchingHistoryId) == 0)
            throw new BusinessException(NO_SUCH_ELEMENT);
        log.info("매칭히스토리 데이터 존재");
        List<MatchedUser> matchedUsers = matchedUserService.findMatchedUsers(matchingHistoryId);
        log.info("매칭된 유저 수 : {}", matchedUsers.size());
        matchedUserService.deleteMatchedUser(matchingHistoryId);
        log.info("매칭유저리스트 데이터 삭제");
        alarmEntityService.deleteAlarm(matchingHistoryId);
        log.info("매칭 완료 알람 데이터 삭제");

        CourtSchedule courtSchedule = courtScheduleService.findScheduleByMatching(matchingHistoryId);// 해당 매칭의 스케줄 조회

        for (MatchedUser matchedUser : matchedUsers) {// 매칭 취소 알림 데이터 생성
            alarmEntityService.saveAlarm(new Alarm(matchedUser.getUserId(), "매칭취소"));
            // 해당 매칭 사용자들 매칭전 상태로 변경
            clientMatchingStatusService.updateUserMatchingStatus(matchedUser.getUserId(), ClientMatchingStatus.builder().status(0).build());
        }

        courtScheduleService.cancelCourtSchedule(courtSchedule.getId()); // 예약 했던 스케줄 일정 취소

        matchingHistoryRepository.deleteMatchingHistory(matchingHistoryId); // 매칭 히스토리 삭제
        log.info("매칭 히스토리 데이터 삭제");
    }

    @Override
    public List<MatchingHistoryRes> findAllMatchingHistoryByUserId(Integer userId, Integer page, Integer size) {
        int start = (page - 1) * size + 1; // 시작 번호 계산
        int end = page * size; // 끝번호 계산
        return matchingHistoryRepository.findAllMatchingHistoryByUserId(userId, start, end);
    }

    @Override
    public void updateMatchingHistoryStatus(Integer matchingHistoryId, MatchingHistoryStatusReq matchingHistoryStatusReq) {
        matchingHistoryRepository.updateMatchingHistoryStatus(matchingHistoryId, matchingHistoryStatusReq);
    }

    @Override
    public void increaseConfirmUserCount(Integer matchingHistoryId) {
        matchingHistoryRepository.increaseConfirmUserCount(matchingHistoryId);
    }

    @Override
    public Integer findConfirmUserCount(Integer matchingHistoryId) {
        return matchingHistoryRepository.findConfirmUserCount(matchingHistoryId);
    }

    @Override
    public Boolean checkUserInMatching(Integer userId, Integer matchingHistoryId) {
        return matchingHistoryRepository.checkUserInMatching(userId, matchingHistoryId) != 0;
    }


}
