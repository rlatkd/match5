package site.match5.domain.client.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.client.dto.Client;
import site.match5.domain.client.dto.UpdateExpReq;
import site.match5.domain.client.dto.UpdateMoneyReq;
import site.match5.domain.client.dto.UpdatePointReq;
import site.match5.domain.client.service.ClientService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ClientController { // client == user

    private final ClientService clientService;

//    // 단일 - 유저 경험치 업데이트  [ 레벨 등급에 대한 변화는 트리거로 처리 ]
//    @PatchMapping("/{userId}/exp")
//    ResponseEntity<String> updateLevelExp(@PathVariable Integer userId, @Valid @RequestBody UpdateExpReq updateExpReq) {
//        clientService.updateLevelExp(userId, updateExpReq);
//        return ResponseEntity.ok("success");
//    }

//    // 단일 - 유저 포인트 업데이트
//    @PatchMapping("/{userId}/point")
//    ResponseEntity<String> updatePointExp(@PathVariable Integer userId, @Valid @RequestBody UpdatePointReq updatePointReq) {
//        clientService.updatePointExp(userId, updatePointReq);
//        return ResponseEntity.ok("success");
//    }
//
    // 단일 - 유저 돈 업데이트
    @PatchMapping("/{userId}/money")
    ResponseEntity<String> updateMoneyExp(@PathVariable Integer userId, @Valid @RequestBody UpdateMoneyReq updatePointReq) {
        clientService.updateMoneyExp(userId, updatePointReq);
        return ResponseEntity.ok("success");
    }

//    // 단일 - 유저 조회
    @GetMapping("/{userId}")
    ResponseEntity<Client> findClient(@PathVariable Integer userId) {
        return ResponseEntity.ok(clientService.findClient(userId));
    }


}
