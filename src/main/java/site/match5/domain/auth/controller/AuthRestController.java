package site.match5.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.auth.dto.AuthCodeDto;
import site.match5.domain.auth.dto.KakaoIdDto;
import site.match5.domain.auth.dto.KakaoTokenDto;
import site.match5.domain.auth.dto.UserAndKaKao;
import site.match5.domain.auth.repository.AuthMapper;
import site.match5.domain.auth.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/kakao")
public class AuthRestController {

    private final AuthService authService;
    private final AuthMapper authMapper;

    @PostMapping("/token")
    public ResponseEntity<KakaoTokenDto> getKakaoAccessToken(@Valid @RequestBody AuthCodeDto codeDto) {
        KakaoTokenDto kakaoTokenDto = authService.getKakaoAccessToken(codeDto.getCode());
        return ResponseEntity.ok(kakaoTokenDto);
    }

    @PostMapping("/info")
    public ResponseEntity<KakaoIdDto> getKakaoUserInfo(@Valid @RequestBody KakaoTokenDto kakaoTokenDto) {
        KakaoIdDto kakaoIdDto = authService.getKakaoUserInfo(kakaoTokenDto);
        return ResponseEntity.ok(kakaoIdDto);
    }

    @PostMapping("/check")
    public ResponseEntity<String> login(@Valid @RequestBody KakaoIdDto kakaoIdDto, HttpServletRequest request) {
        String authenticatedId = authService.getAuthentication(kakaoIdDto);
        if (authenticatedId != null) { //db에서 조회했을 떄 인증받은 사용자(기존에 회원가입하여 kakaoId가 db에 있는사람)가 아니라면 세션 발급
            HttpSession session = request.getSession();
            int userId = authMapper.getUserIdByKakaoId(authenticatedId);
            session.setAttribute("userId", userId);
            session.setAttribute("authenticatedId", authenticatedId);
            return ResponseEntity.ok(authenticatedId);
        }
        return null;
    }

    @PostMapping("/close")
    public ResponseEntity<String> closeKakaoAccess(@Valid @RequestBody KakaoTokenDto kakaoTokenDto) {
        authService.closeKakaoAccess(kakaoTokenDto);
        return ResponseEntity.ok("close access: successful");
    }

    @GetMapping("/ids")
    public ResponseEntity<UserAndKaKao> getUserAndKaKaoId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            String kakaoId = (String) session.getAttribute("authenticatedId");
            UserAndKaKao userAndKaKao = new UserAndKaKao();
            userAndKaKao.setUserId(userId);
            userAndKaKao.setKakaoId(kakaoId);
            return ResponseEntity.ok(userAndKaKao);
        }
        return null;
    }






}
