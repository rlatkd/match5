package site.match5.domain.match.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.match5.domain.alarm.service.MatchingAlarmService;
import site.match5.domain.match.service.MatchCourtReserveService;
import site.match5.domain.match.service.MatchingService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Slf4j
public class TestController {
    private final MatchCourtReserveService matchCourtReserveService;
    private final MatchingAlarmService matchingAlarmService;
    private final MatchingService matchingService;

    @GetMapping("1")
        // 구장 예약 및 매칭 히스토리 생성
    void test() {

//        matchCourtReserveService.courtReserveAndCreateMatchingHistory();

    }

    @GetMapping("2")
    void test2() { // 매칭 완료 알람 데이터 생성
        List<Integer> userList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            userList.add(i);
        }
        matchingAlarmService.saveMatchingCompleteAlarm(60, userList);
    }

    @GetMapping("/3")
    void test3() {
        log.info("테스트 api 실행");
        matchingService.performMatching();
    }
}
