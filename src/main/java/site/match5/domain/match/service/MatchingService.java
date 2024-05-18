package site.match5.domain.match.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.match5.domain.alarm.service.MatchingAlarmService;
import site.match5.domain.alarm.service.NotificationService;
import site.match5.domain.client.repository.ClientMapper;
import site.match5.domain.match.dto.MatchingQueueItem;
import site.match5.domain.match.dto.MatchingWaitingListItem;
import site.match5.domain.match.dto.ReserveAndHistoryDto;
import site.match5.domain.match.repository.MatchingReserveMapper;
import site.match5.domain.match.util.MatchCancleLogManager;
import site.match5.domain.match.util.MatchDataManager;
import site.match5.domain.match.repository.PrepareDataMapper;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoReq;
import site.match5.domain.matchedUser.dto.MatchedUser;
import site.match5.domain.matchedUser.service.MatchedUserService;
import site.match5.domain.matchingHistory.service.MatchingHistoryService;
import site.match5.global.exception.customException.BusinessException;

import java.io.IOException;
import java.util.*;

import static site.match5.global.exception.errorCode.CommonErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchingService {
    private final PrepareDataMapper prepareDataMapper;  //mapper
    private final MatchDataManagerService matchDataManagerService;
    private final MatchDataManager matchDataManager;
    private final MatchCancleLogManager matchCancleLogManager;
    private final MatchCourtReserveService matchCourtReserveService; // 구장 예약 및 매칭 히스토리 생성
    private final MatchingHistoryService matchingHistoryService; // 매칭된 유저들 매칭유저 테이블에 넣기
    private final MatchingAlarmService matchingAlarmService; // 매칭 완료 알람 데이터 생성용
//    private final MatchingHistoryMapper matchingHistoryRepository; // 매칭된 유저 유저리스트 가져오기용
    private final MatchingReserveMapper matchingReserveMapper; // 매칭된 사용자 매칭큐에서 제거하기용
    private final ClientMapper clientMapper;
    private final NotificationService notificationService;
    private final MatchedUserService matchedUserService;


    //자동 매칭 알고리즘 수행 매서드
    public void performMatching() {
        //알고리즘 동작 순서
        updateCancelledUserInMatchData();   // 취소를 수행한 유저 정보를 matchData에 업데이트
        updateNewUserInMatchData(); //매칭 큐 테이블에 새로 추가된 유저 정보를 matchData에 업데이트
        runMatchingAlgorithm(); //자동 매칭 알고리즘 수행 (팀매칭, 팀 나누기, 구장 선택 matchData 업데이트 포함)
    }

    //1. 취소를 수행한 유저 정보를 matchData에 업데이트
    private void updateCancelledUserInMatchData() {
        log.info("updateCancelledUserInMatchData 시작");
        int matchCancleLogIndex = matchCancleLogManager.getMatchCancleLogIndex();   //cancleLogNum 값을 가져온다.
        matchCancleLogManager.setMatchCancleLogIndex((matchCancleLogManager.getMatchCancleLogIndex() + 1) % 2); //cancleLogNum 값을 변경
        Set<Integer> matchCancleLog = matchCancleLogManager.getMatchCancleLogArray()[matchCancleLogIndex];  //취소한 유저 id를 기록한 set을 가져온다.

        //가져온 데이터로 Match 관련 데이터를 업데이트 한다.
        List<MatchingWaitingListItem> items = new ArrayList<>();
        for(MatchingWaitingListItem item : matchDataManager.getMatchingWaitingList()) {
            if (matchCancleLog.contains(item.getUserId())) {
                items.add(item);
            }
        }

        //matchingQueueMap, matchingWaitingList에서 데이터를 찾아서 제거
        //아래 로직은 MatchDataManagerService로 보내기
        for (MatchingWaitingListItem item : items) {
            if (item != null) {
                int index = findIndexFromList(matchDataManager.getMatchingQueueMap().get(item.getSelectedDate()).get(item.getLocation()), item.getUserId());
                matchDataManager.getMatchingQueueMap().get(item.getSelectedDate()).get(item.getLocation()).remove(index);
                matchDataManager.getMatchingWaitingList().remove(item);
//                matchDataManager.getMatchingQueueMap().get(item.getSelectedDate()).get(item.getLocation()).remove(item.getUserId());
            }
        }

        //업데이트가 끝나면 사용한 matchCancleLogArray 초기화
        matchCancleLog.clear();
        log.info("updateCancelledUserInMatchData 끝");
    }

    //2. 매칭 큐 테이블에 새로 추가된 유저 정보를 matchData에 업데이트
    private void updateNewUserInMatchData() {
        log.info("updateNewUserInMatchData 시작");
//        log.info("matchDataManager : {}", matchDataManager.getMatchingWaitingList());
        LinkedList<MatchingWaitingListItem> items = (LinkedList<MatchingWaitingListItem>) matchDataManager.getMatchingWaitingList();
        MatchingWaitingListItem lastItem = items.getLast();
        matchDataManagerService.updateDataFromDatabase(lastItem.getCreatedAt());
        matchDataManagerService.MatchingQueueListSort();

        log.info("updateNewUserInMatchData 끝");
    }

    //3. 매칭 알고리즘 수행
    private void runMatchingAlgorithm() {
        log.info("매칭 알고리즘시작");
        List<MatchingWaitingListItem> waitingList = matchDataManager.getMatchingWaitingList();
        Set<Integer> matchIdSet = matchDataManager.getMatchingUserIdSet();

        if (waitingList.isEmpty()) return;

        //매칭 대기 시간 순 pivot 선정
        for (MatchingWaitingListItem pivotUser : waitingList) {
            if (matchIdSet.contains(pivotUser.getUserId())) continue;   //이미 매칭이 성사된 경우

            List<MatchingQueueItem> selectedList = matchDataManager.getMatchingQueueMap().get(pivotUser.getSelectedDate()).get(pivotUser.getLocation());
            int pivot = findIndexFromList(selectedList, pivotUser.getUserId());

            //pivot user 기준 팀 선정
            List<MatchingQueueItem> bestMemberList = findMatchingTeam(selectedList, pivot);
            if (bestMemberList == null) continue;

            //matchData 업데이트 : matchingUserIdSet 추가
            for (MatchingQueueItem Item : bestMemberList) {
                matchIdSet.add(Item.getUserId());
            }

            //matchData 업데이트 : matchingQueueMap 의 LinkedList 데이터 제거
            int delIndex = selectedList.size() - 1;
            while (delIndex >= 0) {
                if (matchIdSet.contains(selectedList.get(delIndex).getUserId())) {
                    selectedList.remove(delIndex);
                }
                delIndex--;
            }
        }

        //matchData 업데이트 : matchingWaitingList 데이터 업데이트
        int top = waitingList.size() - 1;
        while (top >= 0) {
            if (matchIdSet.contains(waitingList.get(top).getUserId())) waitingList.remove(top);
            top--;
        }

        log.info("남은 매칭 대기 인원 : {}", waitingList.size());
//        log.info("-----2024-05-18 강동구 남은 지역 인원 : {}", matchDataManager.getMatchingQueueMap().get("2024-05-18").get("강동구").size());
//        log.info("-----2024-05-18 양천구 남은 지역 인원 : {}", matchDataManager.getMatchingQueueMap().get("2024-05-18").get("양천구").size());
//        log.info("-----2024-05-18 영등포구 남은 지역 인원 : {}", matchDataManager.getMatchingQueueMap().get("2024-05-18").get("영등포구").size());
//        log.info("-----2024-05-18 노원구 남은 지역 인원 : {}", matchDataManager.getMatchingQueueMap().get("2024-05-18").get("노원구").size());
        log.info("-----2024-05-18 송파구 남은 지역 인원 : {}", matchDataManager.getMatchingQueueMap().get("2024-05-18").get("송파구").size());
//        log.info("-----2024-05-18 은평구 남은 지역 인원 : {}", matchDataManager.getMatchingQueueMap().get("2024-05-18").get("은평구").size());
//        log.info("-----2024-05-18 도봉구 남은 지역 인원 : {}", matchDataManager.getMatchingQueueMap().get("2024-05-18").get("도봉구").size());

        //사용한 HashSet 비우기
        matchIdSet.clear();
        log.info("매칭 알고리즘 종료");
    }

    // 가능한 팀들을 구성한다.
    private List<MatchingQueueItem> findMatchingTeam(List<MatchingQueueItem> selectedList, int pivot) {
        log.info("팀 구성 시작 ");
        MatchingQueueItem pivotItem = selectedList.get(pivot);
        int rightIndex = pivot;

        //기준 범위
        int rightIndexRange = (pivot + 10) < selectedList.size() ? pivot + 10 : selectedList.size() - 1;  //rightIndex 범위

        int expRange = 200; //실력 기준 범위 200
        int ageRange = 10;  //pivot 기준 10살 내
        int timeRange = 2;  //pivot 기준 4시간 내

        //매니저 가능 인원
        int managerCount = 0;   //매니저 여부 카운트
        Set<Integer> managerUserIdSet = new HashSet<>();    //매니저 id 저장하는 set 객체 생성
        if (pivotItem.getIsManager() == 1) {
            managerUserIdSet.add(pivotItem.getUserId());
            managerCount++;
        }

        //가장 최적으로 선택된 팀 구성
        List<MatchingQueueItem> bestMemberList = new ArrayList<>();

        //임시 팀 구성 (실력 순 내림차순)
        LinkedList<MatchingQueueItem> tempMemberList = new LinkedList<>();
        tempMemberList.add(pivotItem);

        //경험치 차이 (초기값은 최대)
        int expDiff = 1001;

        //첫 탐색
        for (int leftIndex = rightIndex - 1; leftIndex >= 0; leftIndex--) {
            if (!levelDiffCheck(selectedList.get(leftIndex), selectedList.get(rightIndex), expRange)) break; //실력 조건 (조건에 맞지 않으면 그 순간 해당 팀 구성 중지)
            if (!outdoorCheck(selectedList.get(leftIndex), pivotItem.getIsOutdoor())) continue; //실내외 여부
            if (!genderCheck(selectedList.get(leftIndex), pivotItem)) continue; //성별 여부
            if (!ageCheck(selectedList.get(leftIndex), pivotItem, ageRange)) continue;  //연령 조건
            if (!timeCheck(selectedList.get(leftIndex), pivotItem, timeRange)) continue;    //시간 조건
            managerCount += managerAdd(selectedList.get(leftIndex), managerUserIdSet);    //매니저 판별, 매니저면 count + 1

            tempMemberList.add(selectedList.get(leftIndex));
            if (tempMemberList.size() == 10) {
                if (managerCount >= 2) {    //매니저가 2명 이상이어야 한다.
                    expDiff = expDiffCalc(tempMemberList);
                    bestMemberList.addAll(tempMemberList);
                }
                break;  //인원이 10명이 되면 순회 종료
            }
        }
        log.info(" 첫 탐색 끝 ");

        //이후 탐색 (이후 생성될 여러 그룹들과 첫번째 그룹을 비교)
        for (int top = rightIndex + 1; top <= rightIndexRange; top++) {
            MatchingQueueItem selectedItem = selectedList.get(top);
            if (!levelDiffCheck(pivotItem, selectedItem, expRange)) break; // 만일 pivot 과 비교하여 차이가 크면 종료
            if (!outdoorCheck(selectedItem, pivotItem.getIsOutdoor())) continue; //실내외 여부
            if (!genderCheck(selectedItem, pivotItem)) continue; //성별 여부
            if (!ageCheck(selectedItem, pivotItem, ageRange)) continue;  //연령 조건
            if (!timeCheck(selectedItem, pivotItem, timeRange)) continue;    //시간 조건
            managerCount += managerAdd(selectedItem, managerUserIdSet);    //매니저 판별, 매니저면 count + 1

            //조건을 모두 만족하는 경우
            tempMemberList.addFirst(selectedItem);  //맨 앞에 요소 삽입
            if (tempMemberList.size() > 10) {
                //마지막 요소 제거 및 매니저 판별
                managerCount -= managerRemove(tempMemberList.removeLast(), managerUserIdSet);
            }

            if (tempMemberList.size() == 10) {
                if (managerCount < 2) continue; //매니저 수 판별

                //경험치 차이 비교 후, 최적이면 bestMemeberList 변경
                int tempExpDiff = expDiffCalc(tempMemberList);
                if (expDiff > tempExpDiff) {
                    expDiff = tempExpDiff;
                    bestMemberList.clear();
                    bestMemberList.addAll(tempMemberList);
                }
            }
        }

        //조건을 만족하는 그룹을 찾은 경우
        if (!bestMemberList.isEmpty()) {
            log.info(" 조건 만족 찾았다. ");
            //가장 실력이 좋은 매니저 2명을 선정
            List<Integer> managerIds = managersfind(bestMemberList, managerUserIdSet);
            if (managerIds == null || managerIds.size() < 2) return null;   //예외 처리

            //지역, 일정, 시간, hmanager, amanager 정보를 DTO에 담기
            ReserveAndHistoryDto reserveAndHistoryDto = ReserveAndHistoryDto.builder()
                    .location(pivotItem.getLocation())
                    .selectedDate(pivotItem.getSelectedDate() + " 00:00:00")
                    .selectedTime(pivotItem.getSelectedTime())
                    .hManagerId(managerIds.get(1))  //home팀은 더 못하는 사람
                    .aManagerId(managerIds.get(0))  //away팀은 더 잘하는 사람
                    .build();
            //log.info("hhhhhhhhhhhhhhhhh {}", pivotItem.getSelectedDate());

            //DTO을 쿼리문에 사용하여, 풋살장 예약 가능 여부 리턴
            int matchingHistoryId = matchCourtReserveService.courtReserveAndCreateMatchingHistory(reserveAndHistoryDto);
            if (matchingHistoryId == -1) return null;  //예외처리 (예매 가능한 풋살장이 없는 경우)

            //팀을 나눠서 객체에 저장. (건네줘야 할건, (List<MatchingQueueItem> bestMemberList, List<Integer> managerIds))
            MatchedTeamInfoReq matchedTeamInfoReq = generateMatchedTeamInfoReq(bestMemberList, managerIds);

            //유저 정보를 DTO에 담아서 쿼리문 날리기 (매칭된 유저들 매칭유저 테이블에 넣기)
            List<Integer> aTeamUsersId = matchedTeamInfoReq.getATeamUsersId();
            List<Integer> hTeamUsersId = matchedTeamInfoReq.getHTeamUsersId();
            for(int index = 0; index < aTeamUsersId.size(); index++) {
                matchedUserService.saveMatchedUser(new MatchedUser(aTeamUsersId.get(index),matchingHistoryId,"A"));
                matchedUserService.saveMatchedUser(new MatchedUser(hTeamUsersId.get(index),matchingHistoryId,"H"));
            }


//            log.info("matchingHistoryId: {}", matchingHistoryId);
//            List<Integer> userIdList = matchingHistoryRepository.findUserIdList(matchingHistoryId);
//            log.info("ddddddddd: {}", userIdList.toString());


            //유저 정보를 DTO에 담아서 쿼리문 날리기 (알림 테이블)
            List<Integer> userIdList = bestMemberList.stream().map(MatchingQueueItem::getUserId).toList();
            log.info("매칭된 유저 아이디 : {}",userIdList.toString());
            matchingReserveMapper.deleteMatchedUserFromQ(userIdList); // 매칭된 인원들 매칭큐에서 삭제
            matchingAlarmService.saveMatchingCompleteAlarm(matchingHistoryId, userIdList);
            List<String> kakaoIdList = clientMapper.getKakaoIdListFromUserIdList(userIdList);
            for(String kakaoId : kakaoIdList) {
                log.info("kakaoId {}", kakaoId);
                notificationService.notifyMessage(kakaoId);
            }
            return bestMemberList;
        }
        return null;
    }

    //경험치 판별
    private boolean levelDiffCheck(MatchingQueueItem selectedLeftItem, MatchingQueueItem selectedRightItem, int expRange) {
        return (selectedRightItem.getCurrentExp() - selectedLeftItem.getCurrentExp() <= expRange);
    }

    //실내 여부 판별 (pivot 기준, [실외 또는 상관없음의 경우 - 실내만 미포함], [실내의 경우 - 실외만 미포함]
    private boolean outdoorCheck(MatchingQueueItem selectedItem, int isOutdoor) {
        if (isOutdoor == 1 || isOutdoor == 2) return selectedItem.getIsOutdoor() != 0;
        else if (isOutdoor == 0) return selectedItem.getIsOutdoor() != 1;
        return true;
    }

    //성별 여부
    private boolean genderCheck(MatchingQueueItem selectedItem, MatchingQueueItem pivotItem) {
        return (selectedItem.getGender() == pivotItem.getGender());
    }

    //연령조건 확인 (pivot 기준, 조건에 맞지 않으면 해당 인원은 미포함)
    private boolean ageCheck(MatchingQueueItem selectedItem, MatchingQueueItem pivotItem, int ageRange) {
        return (Math.abs(pivotItem.getAge() - selectedItem.getAge()) <= ageRange);
    }

    //시간 조건 확인 (pivot 기준, 조건에 맞지 않으면 해당 인원 미포함)
    private boolean timeCheck(MatchingQueueItem selectedItem, MatchingQueueItem pivotItem, int timeRange) {
        return (Math.abs(pivotItem.getSelectedTime() - selectedItem.getSelectedTime()) <= timeRange);
    }

    //매니저 추가
    private int managerAdd(MatchingQueueItem selectedItem, Set<Integer> managerUserIdSet) {
        if (selectedItem.getIsManager() == 1) {
            managerUserIdSet.add(selectedItem.getUserId());
            return 1;
        }
        return 0;
    }

    //매니저 제거
    private int managerRemove(MatchingQueueItem selectedItem, Set<Integer> managerUserIdSet) {
        if (selectedItem.getIsManager() == 1) {
            managerUserIdSet.remove(selectedItem.getUserId());
            return 1;
        }
        return 0;
    }

    //매니저 찾기
    private List<Integer> managersfind(List<MatchingQueueItem> bestMemberList, Set<Integer> managerUserIdSet) {
        List<Integer> managerIds = new ArrayList<>();
        for (MatchingQueueItem Item : bestMemberList) {
            if (!managerUserIdSet.contains(Item.getUserId())) continue;
            managerIds.add(Item.getUserId());
            if (managerIds.size() == 2) return managerIds;
        }
        return null;
    }

    //경험치 차이 계산
    private int expDiffCalc(LinkedList<MatchingQueueItem> tempMemberList) {
        return tempMemberList.getFirst().getCurrentExp() - tempMemberList.getLast().getCurrentExp();
    }

    //LinkedList<matchingQueueItem>에서 pivot에 해당하는 요소 찾기
    private int findIndexFromList(List<MatchingQueueItem> selectedList, int userId) {
        for (int i = 0; i < selectedList.size(); i++) {
            if (selectedList.get(i).getUserId() == userId) return i;
        }
        return -1;
    }

    //팀선정
    private MatchedTeamInfoReq generateMatchedTeamInfoReq(List<MatchingQueueItem> bestMemberList, List<Integer> managerIds) {
        List<Integer> hTeamusers = new ArrayList<>();   //home: 0 3 4 7 List(1)
        List<Integer> aTeamusers = new ArrayList<>();   //away: 1 2 5 6 List(0)
        int count = 0;
        for (MatchingQueueItem Item : bestMemberList) {
            if (Item.getUserId() == managerIds.get(1)) {
                hTeamusers.add(Item.getUserId());
            } else if (Item.getUserId() == managerIds.get(0)) {
                aTeamusers.add(Item.getUserId());
            } else {
                switch (count) {
                    case 0:
                    case 3:
                    case 4:
                    case 7:
                        hTeamusers.add(Item.getUserId());
                        break;
                    default:
                        aTeamusers.add(Item.getUserId());
                }
                count++;
            }
        }
        MatchedTeamInfoReq matchedTeamInfoReq = new MatchedTeamInfoReq(hTeamusers, aTeamusers);
        return matchedTeamInfoReq;
    }

    //팀선정
//    private void divideTeamMembers(List<MatchingQueueItem> bestMemberList, Set<Integer> managerUserIdSet) {
//        Map<Character, Integer[][]> data = new HashMap<>();
//        data.put('h', new Integer[5][2]);
//        data.put('a', new Integer[5][2]);
//        int managerCount = 0;
//        int hManagerCount = 0;
//        int hIndex = 0;
//        int aIndex = 0;
//        int dIndex = 0;
//
//        for (int index = 0; index < bestMemberList.size(); index++) {
//            Character key = null;
//            int count = 0;
//
//            switch (index) {
//                case 0, 3, 4, 7, 8:
//                    key = 'h';
//                    dIndex = hIndex++;
//                    break;
//                default:
//                    key = 'a';
//                    dIndex = aIndex++;
//            }
//
//            data.get(key)[dIndex][0] = bestMemberList.get(index).getUserId();
//            if (managerCount < 2 && bestMemberList.get(index).getIsManager() == 1) {
//                managerCount++;
//                count = 1;
//                if (key == 'h') hManagerCount++;
//            }
//            data.get(key)[dIndex][1] = count;
//        }
//
//        //home팀의 매니저가 없는 경우
//        if (hManagerCount == 0) swapTeamMember(data, 'a', 'h');
//        else if (hManagerCount == 2) swapTeamMember(data, 'h', 'a');
//
//        System.out.println("--------------------------------------");
//        System.out.println("Key: " + 'h');
//        System.out.println("Value: ");
//        for (Integer[] row : data.get('h')) {
//            for (Integer num : row) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("Key: " + 'a');
//        System.out.println("Value: ");
//        for (Integer[] row : data.get('a')) {
//            for (Integer num : row) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("--------------------------------------");
//    }

//    //매니저인 팀원 교체
//    private void swapTeamMember(Map<Character, Integer[][]> data, Character a, Character b) {
//        Integer[][] aArr = data.get(a);
//        Integer[][] bArr = data.get(b);
//        for (int index = 0; index < aArr.length; index++) {
//            if (aArr[index][1] == 1) {
//                int temp = aArr[index][0];
//                aArr[index][0] = bArr[index][0];
//                aArr[index][1] = 0;
//                bArr[index][0] = temp;
//                bArr[index][1] = 1;
//                return;
//            }
//        }
//    }


//    private int binarySearch(List<MatchingQueueItem> sortedList, MatchingQueueItem pivotItem) {
//        int left = 0;
//        int right = sortedList.size() - 1;
//
//        while (left <= right) {
//            int mid = (left + right) / 2;
//
//            if (sortedList.get(mid).getCurrentExp() == pivotItem.getCurrentExp()) {
//                return mid;
//            } else if (sortedList.get(mid).getCurrentExp() < pivotItem.getCurrentExp()) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return -1;
//    }
}
