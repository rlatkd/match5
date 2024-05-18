package site.match5.domain.userReview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.userReview.entity.UserReview;
import site.match5.domain.userReview.service.UserReviewService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review/user")
public class UserReviewController {

    private final UserReviewService userReviewService;

    // 리스트 - 유저평가 생성 [ 동료후기 여러개 작성 + 경험치 적용 + [레벨업 아이디 갱신 트리거] + 동료후기 작성한 사람에게 포인트 지급]
    @PostMapping("/all")
    ResponseEntity<String> saveUserReviews(@Valid @RequestBody List<UserReview> userReviewList) {
        userReviewService.saveUserReviews(userReviewList);
        return ResponseEntity.ok("success");
    }

    // 단일 - 유저평가 생성 [ 동료후기 여러개 작성 + 경험치 적용 + [레벨업 아이디 갱신 트리거] + 동료후기 작성한 사람에게 포인트 지급]
    @PostMapping
    ResponseEntity<String> saveUserReview(@Valid @RequestBody UserReview userReview) {
        userReviewService.saveUserReview(userReview);
        return ResponseEntity.ok("success");
    }
//
//    // 유저 평가 수정
//    @PatchMapping("{userReviewId}")
//    ResponseEntity<String> updateUserReview(@PathVariable Long userReviewId, @Valid @RequestBody UserReview userReview) {
//
//    }

    // 유저 평가 삭제


    // 이미 유저 평가 작성했는지 확인
    @GetMapping("/match/{userId}/{matchHistoryId}")
    ResponseEntity<Boolean> checkUserAlreadyReview(@PathVariable Integer userId ,@PathVariable Integer matchHistoryId) {
        return ResponseEntity.ok(userReviewService.checkUserAlreadyReview(userId,matchHistoryId));
    }



}
