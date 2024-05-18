package site.match5.domain.userReview.repository;

import org.apache.ibatis.annotations.Param;
import site.match5.domain.userReview.entity.UserReview;

import java.util.List;
import java.util.Optional;

public interface UserReviewRepository {
    // 리스트 - 유저 평가 생성
    void saveUserReviews(List<UserReview> userReview);
    // 단일 - 유저 평가 생성
    void saveUserReview(UserReview userReview);
    // 이미 유저 평가 작성했는지 확인
    Integer checkUserAlreadyReview(Integer userId,Integer matchHistoryId);
}
