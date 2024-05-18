package site.match5.domain.client.controller;

import jakarta.validation.Valid;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.match5.domain.client.dto.ClientInfoDto;
import site.match5.domain.client.service.MypageService;
import site.match5.domain.client.service.S3Service;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mypage")
@Slf4j
public class MyPageRestController {

    private final MypageService mypageService;
    private final S3Service s3Service;

    @GetMapping("/info")
    public ResponseEntity<ClientInfoDto> getClientInfo(@Valid @SessionAttribute("authenticatedId") String kakaoId ,@SessionAttribute("userId")int userId) {
        ClientInfoDto clientInfoDto = mypageService.getClientInfo(kakaoId);
        log.info("세션에서 반환된 유저 아이디 : {}", userId);
        return ResponseEntity.ok(clientInfoDto);
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getClientProfileImage(@Valid @SessionAttribute("authenticatedId") String kakaoId) {
        String imageUrl = mypageService.getClientProfileImage(kakaoId);
        return ResponseEntity.ok(imageUrl);
    }

    @PutMapping("/profile")
    public ResponseEntity<ClientInfoDto> updateClientProfileImage(@Valid @RequestParam("file") MultipartFile file, @SessionAttribute("authenticatedId") String kakaoId) throws IOException {
        String imageUrl = s3Service.uploadFile(file);
        mypageService.updateClientProfileImage(kakaoId, imageUrl);
        return ResponseEntity.ok().build();
    }

}