package site.match5.domain.clientMatchingStatus.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.clientMatchingStatus.dto.ClientMatchingStatus;
import site.match5.domain.clientMatchingStatus.service.ClientMatchingStatusService;
import site.match5.global.validation.annotation.CMatchingStatus;

@RestController
@RequestMapping("/api/user/match/status")
@RequiredArgsConstructor
public class ClientMatchingStatusController {
    private final ClientMatchingStatusService clientMatchingStatusService;

//    // 유저 매칭 상태 업데이트
//    @PatchMapping("/{userId}")
//    ResponseEntity<String> updateUserMatchingStatus(@PathVariable Integer userId,@Valid @RequestBody ClientMatchingStatus clientMatchingStatus) {
//        clientMatchingStatusService.updateUserMatchingStatus(userId,clientMatchingStatus);
//        return ResponseEntity.ok("success");
//    }
//
//    // 유저 매칭 상태 조회
//    @GetMapping("/{userId}")
//    ResponseEntity<ClientMatchingStatus> findClientMatchingStatus(@PathVariable Integer userId) {
//        return ResponseEntity.ok(clientMatchingStatusService.findClientMatchingStatus(userId));
//    }

}
