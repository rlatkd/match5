package site.match5.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping("/mymatch")
    public String mymatch() {
        return "/mymatch";
    }

    @GetMapping("/cash-charge")
    public String cash() {
        return "/cash-charge";
    }

    @GetMapping("/my-court-review-list")
    public String myCourtReviewList() {
        return "/client/my-court-review-list";
    }

    @GetMapping("/my-court-review-list/detail")
    public String myCourtReviewListDetail() {
        return "/client/my-court-review-list-detail";
    }


}
