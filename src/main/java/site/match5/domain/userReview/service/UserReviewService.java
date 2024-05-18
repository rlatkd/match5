package site.match5.domain.userReview.service;

import site.match5.domain.userReview.entity.UserReview;

import java.util.List;

public interface UserReviewService {
    // 리스트 - 유저 평가 생성
    void saveUserReviews(List<UserReview> userReview);
    // 단일 - 유저 평가 생성
    void saveUserReview(UserReview userReview);
    // 이미 유저 평가 작성했는지 확인
    boolean checkUserAlreadyReview(Integer userId,Integer matchHistoryId);


}
