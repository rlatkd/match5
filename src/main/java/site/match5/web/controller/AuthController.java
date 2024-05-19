package site.match5.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/callback")
    public String callback() {
        return "/auth/callback";
    }

    @GetMapping("/auth")
    public String auth() {
        return "/auth/auth";
    }

}