package site.match5.domain.alarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class TestAlarmPageController {

    @GetMapping("/testalarm")
    public String getAlarm(){
        return "alarm/testalarm.html";
    }
}
