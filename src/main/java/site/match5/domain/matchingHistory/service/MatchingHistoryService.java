package site.match5.domain.matchingHistory.service;

import org.apache.ibatis.annotations.Param;
import site.match5.domain.court.dto.MatchedCourtInfoRes;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoReq;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoRes;
import site.match5.domain.matchingHistory.dto.MatchingHistoryRes;
import site.match5.domain.matchingHistory.dto.MatchingHistoryStatusReq;

import java.util.List;

public interface MatchingHistoryService {


    /* 매칭 확정 비즈니스 로직
    매칭 확정 버튼을 누를때 해당 매칭히스토리가있는건지 확인,
    있으면 확정완료인원 증가시켜준다. 만약 증가전 값이 9라면 증가와 동시에
    결제이력과 ,회원평가 데이터를 생성한다 + 모든 인원의 충전금액을 차감한다.
    + 알람데이터를 생성한다 ( 매칭 확정 ) + 매칭히스토리 상태를 변경한다.
     */
     void confirmMatch(Integer matchingHistoryId, Integer userId);

    /*
    매칭 취소 비즈니스 로직
    매칭 히스토리 아이디를 이용하여 해당 매칭이 존재하는지 확인한다.
    없다면 예외, 있다면 매칭 인원들에서 해당 매칭 인원들 삭제시켜주고 생성된 알림데이터에 삭제하고 난뒤
    매칭히스토리 삭제시켜준다. + 매칭취소 알림 데이터 생성
     */
    void cancelMatch(Integer matchingHistoryId, Integer userId);

    //12 - 내 매칭이력 조회
    List<MatchingHistoryRes> findAllMatchingHistoryByUserId(Integer userId, Integer page, Integer size);

    // 매칭 히스토리 상태를 변경한다.
    void updateMatchingHistoryStatus(Integer matchingHistoryId, MatchingHistoryStatusReq matchingHistoryStatusReq);

    // 단일 - 매칭 확정 인원 증가
    void increaseConfirmUserCount (Integer matchingHistoryId);
    // 단일 - 현재 매칭 확정 인원 조회
    Integer findConfirmUserCount(Integer matchingHistoryId);
    // 단일 - 해당 매칭에 속해있는 유저인지 확인
    Boolean checkUserInMatching(Integer userId,Integer matchingHistoryId);


}
