package site.match5.domain.match.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.match5.domain.match.util.MatchCancleLogManager;
import site.match5.domain.match.util.MatchDataManager;
import site.match5.domain.match.dto.MatchingQueueItem;
import site.match5.domain.match.dto.MatchingWaitingListItem;
import site.match5.domain.match.repository.PrepareDataMapper;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchDataManagerService {
    private final PrepareDataMapper prepareDataMapper;  //mapper
    private final MatchDataManager matchDataManager;
    private final MatchCancleLogManager matchCancleLogManager;


    // 데이터베이스에서 데이터를 가져와서 MatchingDataManager의 멤버 필드를 초기화하는 메서드
    // 서버 실행 시 처음에 한 번만 실행
    @PostConstruct
    public void initializeDataFromDatabase() {
        List<MatchingQueueItem> itemList = prepareDataMapper.selectAll();
        matchDataManager.getMatchingWaitingList().clear();
        matchDataManager.getMatchingQueueMap().clear();
        for (MatchingQueueItem item : itemList) {
            addMatchingWaitingList(item);  // waitingListItems 에 들어갈 데이터 추가
            putMatchingQueueMap(item);  // matchingQueueMap 에 들어갈 데이터 추가
        }
    }

    // 스케쥴러로 일정 시간마다 새로 추가된 데이터 서버에 추가
    public void updateDataFromDatabase(Instant createdAt) {
        List<MatchingQueueItem> itemList = prepareDataMapper.selectNewData(createdAt);
        for (MatchingQueueItem item : itemList) {
            addMatchingWaitingList(item);  // waitingListItems 에 들어갈 데이터 추가
            putMatchingQueueMap(item);  // matchingQueueMap 에 들어갈 데이터 추가
        }
    }

    private void addMatchingWaitingList(MatchingQueueItem item) {
        MatchingWaitingListItem waitingListItem = MatchingWaitingListItem.builder()
                .userId(item.getUserId())
                .createdAt(item.getCreateAt())
                .selectedDate(item.getSelectedDate())
                .location(item.getLocation())
                .build();
        matchDataManager.getMatchingWaitingList().add(waitingListItem);
    }
    private void putMatchingQueueMap(MatchingQueueItem item) {
        String selectedDate = item.getSelectedDate();
        String location = item.getLocation();
//        Integer userId = item.getUserId();

        if (!matchDataManager.getMatchingQueueMap().containsKey(selectedDate)) {
            matchDataManager.getMatchingQueueMap().put(selectedDate, new HashMap<>());
        }

        Map<String, List<MatchingQueueItem>> locationQueueMap = matchDataManager.getMatchingQueueMap().get(selectedDate);
        if (!locationQueueMap.containsKey(location)) {
            locationQueueMap.put(location, new LinkedList<>());
        }

        List<MatchingQueueItem> innerList = matchDataManager.getMatchingQueueMap().get(selectedDate).get(location);
        innerList.add(item);
//        Map<Integer, MatchingQueueItem> userIdQueueMap = matchDataManager.getMatchingQueueMap().get(selectedDate).get(location);
//        if (!userIdQueueMap.containsKey(userId)) {
//            userIdQueueMap.put(userId, item);
//        }d
    }

    //LinkedList 정렬
    public void MatchingQueueListSort() {
        Set<String> daykeys = matchDataManager.getMatchingQueueMap().keySet();
        for (String daykey : daykeys) {
            Set<String> lockeys = matchDataManager.getMatchingQueueMap().get(daykey).keySet();
            for (String lockey : lockeys) {
                LinkedList<MatchingQueueItem> sortedItems = matchDataManager.getMatchingQueueMap().get(daykey).get(lockey)
                        .stream()
                        .sorted(Comparator.comparingInt(MatchingQueueItem::getCurrentExp))
                        .collect(Collectors.toCollection(LinkedList::new));
//                log.info("--------------------------------------------");
//                log.info("userID : {}, level: {}", sortedItems.get(0).getUserId(), sortedItems.get(0).getCurrentExp());
//                log.info("userID : {}, level: {}", sortedItems.get(1).getUserId(), sortedItems.get(1).getCurrentExp());
//                log.info("userID : {}, level: {}", sortedItems.get(2).getUserId(), sortedItems.get(2).getCurrentExp());
//                log.info("userID : {}, level: {}", sortedItems.get(3).getUserId(), sortedItems.get(3).getCurrentExp());
//                log.info("userID : {}, level: {}", sortedItems.get(4).getUserId(), sortedItems.get(4).getCurrentExp());
//                log.info("--------------------------------------------");

                matchDataManager.getMatchingQueueMap().get(daykey).put(lockey, sortedItems);

            }
        }
    }

//    public void deleteMatchData(String selectedDate) {
//        Set[] arr = this.matchCancleLogManager.getCancleLogSet();
//        Iterator iter = arr[this.matchCancleLogManager.getCancleLogNum()].iterator();
//
//        while (iter.hasNext()) {
//            this.matchDataManager
//        }
//    }
}
