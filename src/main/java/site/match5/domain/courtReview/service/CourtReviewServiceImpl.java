package site.match5.domain.courtReview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.courtReview.dto.CourtReviewDetailRes;
import site.match5.domain.courtReview.dto.StadiumAllCourtReviewRes;
import site.match5.domain.courtReview.entity.CourtReview;
import site.match5.domain.courtReview.repository.CourtReviewRepository;
import site.match5.domain.stadium.dto.CourtInfoRes;
import site.match5.domain.stadium.repository.StadiumRepository;
import site.match5.global.exception.customException.BusinessException;

import java.util.List;
import java.util.Map;

import static site.match5.global.exception.errorCode.CommonErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourtReviewServiceImpl implements CourtReviewService {
    private final CourtReviewRepository courtReviewRepository;
    private final StadiumRepository stadiumRepository;

    @Transactional
    @Override
    public void saveCourtReview(Map<String,Object> paramMap) {
        paramMap.put("success_row", 0);
        if(courtReviewRepository.saveCourtReview(paramMap) == 0) throw new BusinessException(SQL_FAIL); //프로시저의 결과는 0==실패 , 1== 성공
    }
    @Transactional
    @Override
    public void updateCourtReviewById(Map<String,Object> paramMap) {
        paramMap.put("success_row", 0);
        findCourtReviewById((Integer) paramMap.get("v_court_review_id")); // 있는 구장평가인지 조회부터해본다.
        if(courtReviewRepository.updateCourtReviewById(paramMap)==0) throw new BusinessException(NO_SUCH_ELEMENT); //프로시저의 결과는 0==실패 , 1== 성공
    }
    @Transactional
    @Override
    public void deleteCourtReviewById(Map<String,Object> paramMap) {
        findCourtReviewById((Integer) paramMap.get("v_court_review_id")); // 있는 구장평가인지 조회부터해본다.
        if(courtReviewRepository.deleteCourtReviewById(paramMap) == 0) throw new BusinessException(NO_SUCH_ELEMENT); //프로시저의 결과는 0==실패 , 1== 성공
    }

    @Override
    public CourtReview findCourtReviewById(Integer courtReviewId) {
        return courtReviewRepository.findCourtReviewById(courtReviewId)
                .orElseThrow(() -> new BusinessException(NO_SUCH_ELEMENT));
        //orElseThrow 를 이용하여 if 문없이 예외처리해서 코드가 깔끔하다, optional 이 비어있으면 예외발생, 값 있으면 반환한다.

    }

    @Override
    public List<CourtReview> findAllCourtReviewByUserId(Integer userId) {
        List<CourtReview> result = courtReviewRepository.findAllCourtReviewByUserId(userId);
        if(result.isEmpty()) throw new BusinessException(NO_SUCH_ELEMENT);
        //반환된 List가 비어있는지 확인한다.
        return result;
    }

    @Override
    public List<StadiumAllCourtReviewRes> findAllCourtReviewByStadiumId(Integer stadiumId) {
        return courtReviewRepository.findAllCourtReviewByStadiumId(stadiumId);
    }

    @Override
    public Float findAvgCourtReviewRateByStadiumId(Integer stadiumId) {
        Float result = courtReviewRepository.findAvgCourtReviewRateByStadiumId(stadiumId);
        if (result ==null){
            return (float) 0;
        }
        return result;
    }

    @Override
    public Integer findStadiumTotalReviewCount(Integer stadiumId) {
        return courtReviewRepository.findStadiumTotalReviewCount(stadiumId);
    }

    @Override
    public CourtReviewDetailRes findCourtReviewDetailById(Integer courtReviewId) {
        CourtInfoRes courtInfoByCourtReview = stadiumRepository.findCourtInfoByCourtReview(courtReviewId);
        CourtReviewDetailRes courtReviewDetailRes = courtReviewRepository.findCourtReviewDetailById(courtReviewId);
        courtReviewDetailRes.setCourtName(courtInfoByCourtReview.getName());
        courtReviewDetailRes.setLat(courtInfoByCourtReview.getLat());
        courtReviewDetailRes.setLng(courtInfoByCourtReview.getLng());
        courtReviewDetailRes.setImages(courtInfoByCourtReview.getImages());
        return courtReviewDetailRes;
    }

    @Override
    public List<CourtReviewDetailRes> findAllCourtReviewDetailById(Integer userId) {
        return courtReviewRepository.findAllCourtReviewDetailById(userId);
    }

    @Override
    public List<CourtReviewDetailRes> findAllCourtReviewDetailByIdPaging(Integer userId, Integer page, Integer size) {
        int start = (page-1) * size +1; // 시작 번호 계산
        int end = page*size; // 끝번호 계산
        return courtReviewRepository.findAllCourtReviewDetailByIdPaging(userId, start, end);
    }
}
