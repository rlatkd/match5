package site.match5.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.match5.domain.auth.dto.KakaoUrlDto;
import site.match5.domain.auth.service.LoginService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class LoginRestController {

    private final LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<KakaoUrlDto> getUrl() {
        KakaoUrlDto url = loginService.getUrl();
        return ResponseEntity.ok(url);
    }

}
