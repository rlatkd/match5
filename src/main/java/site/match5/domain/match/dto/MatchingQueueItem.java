package site.match5.domain.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class MatchingQueueItem {
    private int userId;
    private String selectedDate;    //ex) 2024-05-09
    private int selectedTime;   //ex) 24
    private String location;    //ex) 성북구
    private int isManager;  //0: false, 1: true
    private int isOutdoor;  //0: 실내, 1: 실외, 2: 상관없음
    private Instant createAt; //매칭 시작 시간
    private char gender;    //m: 남자, f: 여자
    private int age;
    private int currentExp; //현재 경험치
}
