package site.match5.domain.match.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import site.match5.domain.match.dto.MatchingQueueItem;
import site.match5.domain.match.dto.MatchingWaitingListItem;

import java.util.*;

@Component
@Getter
@Setter
public class MatchDataManager {
    private final Map<String, Map<String, List<MatchingQueueItem>>> matchingQueueMap;   //ex) {"2024-05-10" : {"성북구": [MatchingQueueItem, ... ], ...} ...}
//    private final Map<String, Map<String, Map<Integer, MatchingQueueItem>>> matchingQueueMap;   //ex) {"2024-05-10" : {"성북구": {1 : MatchingQueueItem}, ... ], ...} ...}
    private final List<MatchingWaitingListItem> matchingWaitingList;   //ex) [MatchingWaitingListItem, ... ]
    private final Set<Integer> matchingUserIdSet; //매칭된 유저 아이디

    public MatchDataManager() {
        this.matchingQueueMap = new HashMap<>();
        this.matchingWaitingList = new LinkedList<>();
        this.matchingUserIdSet = new HashSet<>();
    }
}
