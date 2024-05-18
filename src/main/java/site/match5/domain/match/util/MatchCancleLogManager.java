package site.match5.domain.match.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Getter
@Setter
public class MatchCancleLogManager {
    private int matchCancleLogIndex;   //cancleLogNum 은 0 또는 1 (현재 기록중인 로그 관리 번호)
    private Set<Integer>[] matchCancleLogArray;    //각 배열의 요소에 취소한 유저의 id를 관리

    public MatchCancleLogManager() {
        matchCancleLogIndex = 0;
        matchCancleLogArray = new HashSet[2];
        matchCancleLogArray[0] = new HashSet<>();
        matchCancleLogArray[1] = new HashSet<>();
    }
}
