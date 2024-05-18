package site.match5.domain.alarm.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.alarm.dto.AlarmRes;
import site.match5.domain.alarm.service.MatchingAlarmService;

import java.util.List;

@RestController
@RequestMapping("/api/alarm")
@RequiredArgsConstructor
@Slf4j
public class AlarmController {
    private final MatchingAlarmService matchingAlarmService;



    @GetMapping("/user")
    List<AlarmRes> getAllAlarmByUserId(HttpServletRequest request) { //변수로 userId를 넣어주려면
        HttpSession session = request.getSession(false);
        if(session != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            System.out.println(userId);
            List<AlarmRes> allAlarmByUserId = matchingAlarmService.getAllAlarmByUserId(userId);
//            System.out.println(allAlarmByUserId);
            for (AlarmRes a : allAlarmByUserId) {
//                System.out.println(a.toString());
            }
            return allAlarmByUserId;
        }
        return null;
    }

}
