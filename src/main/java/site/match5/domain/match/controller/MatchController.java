package site.match5.domain.match.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.match.dto.MatchConditionReq;
import site.match5.domain.match.service.MatchQService;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchQService matchQService;
    // 매칭 조건을 파라미터로 받아서 매칭큐에 넣어주는 작업
    @PostMapping("/start")
    ResponseEntity<String> saveSelectedCondToMatchingQ(@RequestBody MatchConditionReq matchConditionReq) {
        matchQService.saveSelectedCondToMatchingQ(matchConditionReq);
        return ResponseEntity.ok("success");
    }
}
