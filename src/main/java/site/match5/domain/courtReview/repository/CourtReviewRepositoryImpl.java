package site.match5.domain.courtReview.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.courtReview.dto.CourtReviewDetailRes;
import site.match5.domain.courtReview.dto.StadiumAllCourtReviewRes;
import site.match5.domain.courtReview.entity.CourtReview;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CourtReviewRepositoryImpl implements CourtReviewRepository {

    private final CourReviewMapper dao;

    @Override
    public Integer saveCourtReview(Map<String,Object> paramMap) {
        return dao.saveCourtReview(paramMap);
    }

    @Override
    public Integer updateCourtReviewById(Map<String,Object> paramMap) {
        return dao.updateCourtReviewById( paramMap);
    }

    @Override
    public Integer deleteCourtReviewById(Map<String,Object> paramMap) {
        return dao.deleteCourtReviewById(paramMap);
    }

    @Override
    public Optional<CourtReview> findCourtReviewById(Integer courtReviewId) {
        return dao.findCourtReviewById(courtReviewId);
    }

    @Override
    public List<CourtReview> findAllCourtReviewByUserId(Integer userId) {
        return dao.findAllCourtReviewByUserId(userId);
    }

    @Override
    public List<StadiumAllCourtReviewRes> findAllCourtReviewByStadiumId(Integer stadiumId) {
        return dao.findAllCourtReviewByStadiumId(stadiumId);
    }

    @Override
    public Float findAvgCourtReviewRateByStadiumId(Integer stadiumId) {
        return dao.findAvgCourtReviewRateByStadiumId(stadiumId);
    }

    @Override
    public Integer findStadiumTotalReviewCount(Integer stadiumId) {
        return dao.findStadiumTotalReviewCount(stadiumId);
    }

    @Override
    public CourtReviewDetailRes findCourtReviewDetailById(Integer courtReviewId) {
        return dao.findCourtReviewDetailById(courtReviewId);
    }

    @Override
    public List<CourtReviewDetailRes> findAllCourtReviewDetailById(Integer userId) {
        return dao.findAllCourtReviewDetailById(userId);
    }

    @Override
    public List<CourtReviewDetailRes> findAllCourtReviewDetailByIdPaging(Integer userId, Integer start, Integer end) {
        return dao.findAllCourtReviewDetailByIdPaging(userId, start, end);
    }
}
