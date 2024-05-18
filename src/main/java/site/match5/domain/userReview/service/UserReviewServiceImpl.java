package site.match5.domain.userReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.client.dto.UpdateExpReq;
import site.match5.domain.client.dto.UpdatePointReq;
import site.match5.domain.client.repository.ClientRepository;
import site.match5.domain.client.service.ClientService;
import site.match5.domain.userReview.entity.UserReview;
import site.match5.domain.userReview.repository.UserReviewMapper;
import site.match5.domain.userReview.repository.UserReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserReviewServiceImpl implements UserReviewService {
    private final UserReviewRepository userReviewRepository;
    private final ClientService clientService; // 다른 도메인의 Service 를 가져와야 예외처리까지 확인할 수 있다.

    // 리스트 - 유저 평가 생성
    @Transactional
    @Override
    public void saveUserReviews(List<UserReview> userReviews) {
        userReviewRepository.saveUserReviews(userReviews);
        for(UserReview userReview : userReviews) {  // 유저 평가가 생성되면 평가된 사람의 레벨 경험치를 업데이트 해줘야한다.
            clientService.updateLevelExp(userReview.getToUserId(),new UpdateExpReq(userReview.getReviewRate()));
            clientService.updatePointExp(userReview.getFromUserId(),new UpdatePointReq(5)); // 리뷰어는 5점의 포인트를 얻는다.
        }
    }

    // 단일 - 유저 평가 생성
    @Transactional
    @Override
    public void saveUserReview(UserReview userReview) {
        userReviewRepository.saveUserReview(userReview);
        clientService.updatePointExp(userReview.getFromUserId(),new UpdatePointReq(5)); // 리뷰어는 5점의 포인트를 얻는다.
    }

    @Override
    public boolean checkUserAlreadyReview(Integer userId,Integer matchHistoryId) {
        Integer row = userReviewRepository.checkUserAlreadyReview(userId,matchHistoryId);
        return row != 0;
    }

    // 유저 평가 수정



}
