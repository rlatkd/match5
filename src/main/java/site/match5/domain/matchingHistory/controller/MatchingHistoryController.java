package site.match5.domain.matchingHistory.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import site.match5.domain.matchingHistory.dto.*;
import site.match5.domain.matchingHistory.service.MatchingHistoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matching")
@Slf4j
public class MatchingHistoryController {

    private final MatchingHistoryService matchingHistoryService;

    // 단일 - 매칭 확정 로직
    @GetMapping("/confirm/{matchingHistoryId}/{userId}")
    ResponseEntity<String> confirmMatch(@PathVariable Integer matchingHistoryId, @PathVariable Integer userId) {

        matchingHistoryService.confirmMatch(matchingHistoryId,userId);
        return ResponseEntity.ok("success");
    }


    // 단일 - 매칭 취소 로직
    @GetMapping("/cancel/{matchingHistoryId}/{userId}")
    ResponseEntity<String> cancelMatch(@PathVariable Integer matchingHistoryId, @PathVariable Integer userId) {
        matchingHistoryService.cancelMatch(matchingHistoryId,userId);
        return ResponseEntity.ok("success");
    }

    // 단일 - 해당 유저 매칭 이력 조회
    @GetMapping("/user/{userId}/{page}/{size}")
    ResponseEntity<List<MatchingHistoryRes>> findAllMatchingHistoryByUserId(@PathVariable Integer userId, @PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(matchingHistoryService.findAllMatchingHistoryByUserId(userId, page, size));
    }

    // 단일 - 매칭 히스토리 상태 변경
    @PatchMapping("/history/{matchingHistoryId}")
    ResponseEntity<String> updateMatchingHistoryStatus(@PathVariable Integer matchingHistoryId,MatchingHistoryStatusReq matchingHistoryStatusReq ) {
        matchingHistoryService.updateMatchingHistoryStatus(matchingHistoryId, matchingHistoryStatusReq);
        return ResponseEntity.ok("success");
    }


    // 단일 - 매칭 확정 인원 증가
    @PatchMapping("/confirm/increase/{matchingHistoryId}")
    ResponseEntity<String> increaseConfirmUserCount (@PathVariable Integer matchingHistoryId) {
        matchingHistoryService.increaseConfirmUserCount(matchingHistoryId);
        return ResponseEntity.ok("success");
    }

    // 단일 - 해당 매칭의 매칭 확정 인원 조회
    @GetMapping("/confirm/count/{matchingHistoryId}")
    ResponseEntity<Integer> findConfirmUserCount(@PathVariable Integer matchingHistoryId) {
        return ResponseEntity.ok(matchingHistoryService.findConfirmUserCount(matchingHistoryId));
    }

    // 단일 - 해당 매칭에 속해있는 유저인지 확인
    @GetMapping("/check/user/{userId}/match/{matchingHistoryId}")
    ResponseEntity<Boolean> checkUserInMatching(@PathVariable Integer userId, @PathVariable Integer matchingHistoryId) {
        return ResponseEntity.ok(matchingHistoryService.checkUserInMatching(userId, matchingHistoryId));
    }

}
