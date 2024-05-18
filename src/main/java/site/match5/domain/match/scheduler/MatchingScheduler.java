
package site.match5.domain.match.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import site.match5.domain.match.service.MatchDataManagerService;
import site.match5.domain.match.service.MatchingService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class MatchingScheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final MatchingService matchingService;


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is MatchingScheduler now {}", dateFormat.format(new Date()));
    }

    @Scheduled( initialDelay = 15000,fixedRate = 15000)
    public void performMatching() {
        //자동 매칭 알고리즘 수행 매서드 호출
        matchingService.performMatching();
    }
}



