package site.match5.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import site.match5.domain.matchingHistory.service.MatchingHistoryService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MatchViewController {
    private final MatchingHistoryService matchingHistoryService;

    @GetMapping("/match_info")
    public String matchInfo(@SessionAttribute("userId")int userId, @RequestParam int matchHistoryId) {
//        int testUserId = 8705;
//        int testMatchHistoryId = 815;
//        userId = testUserId;
//        matchHistoryId = testMatchHistoryId;

        log.info("chekc {}",matchingHistoryService.checkUserInMatching(userId, matchHistoryId));
        if (matchingHistoryService.checkUserInMatching(userId, matchHistoryId)) {
            log.info("매칭히스토리 아이디 {}", matchHistoryId);
            return "match/match_info";
        } else {
            return "redirect:/";
        }
    }

//    @GetMapping("/match_info")
//    public String matchInfo() {
//        return "match/match_info";
//    }

}