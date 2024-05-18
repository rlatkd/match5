package site.match5.domain.userReview.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.userReview.entity.UserReview;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserReviewRepositoryImpl implements UserReviewRepository{
    private final UserReviewMapper dao;
    @Override
    public void saveUserReviews(List<UserReview> userReview) {
        dao.saveUserReviews(userReview);
    }

    @Override
    public void saveUserReview(UserReview userReview) {
        dao.saveUserReview(userReview);
    }

    @Override
    public Integer checkUserAlreadyReview(Integer userId,Integer matchHistoryId) {
        return dao.checkUserAlreadyReview(userId,matchHistoryId);
    }


}
