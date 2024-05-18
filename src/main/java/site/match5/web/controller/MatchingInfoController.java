package site.match5.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchingInfoController {

    @GetMapping("/match_info_detail")
    public String matchDetail() {
        return "/matchingHistory/match_info_detail";
    }

    @GetMapping("/my-match-history-list")
    public String myMatchHistoryList() {
        return "/matchingHistory/my-match-history-list";
    }

//    @GetMapping("/my-match-history-list/detail")
//    public String myMatchHistoryListDetail() {
//        return "/matchingHistory/match_info_detail";
//    }
}