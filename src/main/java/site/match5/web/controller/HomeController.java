package site.match5.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import site.match5.domain.auth.dto.ClientAuthInfoDto;

@Controller
public class HomeController {

    // 홈 화면
    @GetMapping("/")
    public String home() {
        return "/home";
    }
    // 마이페이지
    @GetMapping("/mypage")
    public String mypage() {
        return "/client/mypage";
    }
    // 로그인 페이지
    @GetMapping("/login")
    public String login() { return "/auth/login"; }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signup(@ModelAttribute ClientAuthInfoDto clientAuthInfoDto) {
        return "/auth/signup";
    }
    //매칭 버튼 눌렀을시
    @GetMapping("/start")
    public String start() {
        return "/match/start";
    }

    //구장 정보 눌렀을시 -> 풋살장 정보로 이름바꿔야함.
    @GetMapping("/stadium-list")
    public String stadiumList() {
        return "/stadium/stadium-list";

    }

    @GetMapping("/stadium-list/detail")
    public String stadiumListDetail() {
        return "/stadium/stadium-list-detail";

    }

}
