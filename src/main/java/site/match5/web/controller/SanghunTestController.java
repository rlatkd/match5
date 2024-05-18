package site.match5.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SanghunTestController {

    @GetMapping("/sanghuntest")
    public String sanghuntest() {
        return "sanghuntest";
    }

}
