package site.match5.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    @GetMapping("/peerreview")
    String peerreview() {
        return "userReview/peerreview";
    }
    @GetMapping("/court-review-regist")
    String stadiumreviewwrite() {
        return "court_review_regist";
    }

    @GetMapping("/peerReview")
    String peerReview() { return "/userReview/peer_review"; }

    @GetMapping("/courtReviewRegist")
    String courtReviewRegist() { return "/userReview/court_review_regist"; }

    @GetMapping("/courtReviewUpdate")
    String courtReviewUpdate() { return "/userReview/court_review_update"; }

}
