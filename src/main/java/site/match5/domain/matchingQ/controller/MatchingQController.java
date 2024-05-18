package site.match5.domain.matchingQ.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.match5.domain.matchingQ.dto.MatchingQ;
import site.match5.domain.matchingQ.service.MatchingQService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matchQ")
public class MatchingQController {
    private final MatchingQService matchingQService;


    // 매칭큐에 넣기
    @PostMapping
    ResponseEntity<String> saveMatchingQ(@RequestBody @Valid MatchingQ matchingQ) {
        matchingQService.saveMatchingQ(matchingQ);
        return ResponseEntity.ok("success");
    }
}
