package site.match5.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.auth.dto.ClientAuthInfoDto;
import site.match5.domain.auth.service.SignupService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class SignupRestController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<String> registClient(@Valid @RequestBody ClientAuthInfoDto clientAuthInfoDto) {
            signupService.registClient(clientAuthInfoDto);
            return ResponseEntity.ok("signup: successful");
    }

}
