package site.match5.domain.stadium.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.match5.domain.courtReview.dto.StadiumAllCourtReviewRes;
import site.match5.domain.courtReview.service.CourtReviewService;
import site.match5.domain.stadium.dto.*;
import site.match5.domain.stadium.repository.StadiumRepository;
import site.match5.global.exception.customException.BusinessException;

import java.util.List;

import static site.match5.global.exception.errorCode.CommonErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;
    private final CourtReviewService courtReviewService;

    @Override
    public CourtInfoRes findCourtInfoById(Integer courtId) {
        CourtInfoRes courtInfoRes = stadiumRepository.findCourtInfoById(courtId);
        if (courtInfoRes == null) { // 데이터가 존재하는지 확인
            throw new BusinessException(NO_SUCH_ELEMENT);
        }
        return courtInfoRes;
    }
    @Override
    public List<StadiumNameAndLocRes> findAllStadium(Integer page, Integer size) {
        int start = (page-1) * size +1; // 시작 번호 계산
        int end = page*size; // 끝번호 계산

        List<StadiumNameAndLocRes> allStadium = stadiumRepository.findAllStadium(start, end);
        if(allStadium.isEmpty()) {
           throw new BusinessException(NO_SUCH_ELEMENT);
        }
        return allStadium;
    }

    @Override
    public List<StadiumNameAndLocRes> findAllStadiumByLocation(String location,Integer page, Integer size) {
        int start = (page-1) * size +1; // 시작 번호 계산
        int end = page*size; // 끝번호 계산
        List<StadiumNameAndLocRes> allStadiumByLocation = stadiumRepository.findAllStadiumByLocation(location,start, end);
        if(allStadiumByLocation.isEmpty()) {
            throw new BusinessException(NO_SUCH_ELEMENT);
        }
        return allStadiumByLocation;
    }

    @Override
    public StadiumInfoRes findStadiumInfo(Integer stadiumId) {
        return stadiumRepository.findStadiumInfo(stadiumId);
    }

    // 해당 풋살장 상세 정보 by 풋살장 id  [ 해당 풋살장의 모든 구장 사진, 풋살장 이름, 풋살장 위도경도, 풋살장 평균 평가점수,리뷰참여자수 , 해당 풋살장 모든 구장 리뷰정보(평점,내용,닉네임,시간)
    @Override
    public StadiumDetailInfoRes findStadiumDetailInfo(Integer stadiumId) {
        StadiumInfoRes stadiumInfo = findStadiumInfo(stadiumId); // 풋살장 이름,위도,경도,이미지들
        log.info("stadiumINfo : {} ",stadiumInfo.toString());

        int reviewCount = courtReviewService.findStadiumTotalReviewCount(stadiumId); // 해당 풋살장 리뷰갯수
        List<StadiumAllCourtReviewRes> reviews = courtReviewService.findAllCourtReviewByStadiumId(stadiumId); // 모든 리뷰 (평점,내용,닉네임,생성시간)
        float stadiumAvgRate = courtReviewService.findAvgCourtReviewRateByStadiumId(stadiumId);

        StadiumDetailInfoRes stadiumDetailInfoRes = new StadiumDetailInfoRes();
        stadiumDetailInfoRes.setName(stadiumInfo.getName());
        stadiumDetailInfoRes.setLat(stadiumInfo.getLat());
        stadiumDetailInfoRes.setLng(stadiumInfo.getLng());
        stadiumDetailInfoRes.setImages(stadiumInfo.getImages());
        stadiumDetailInfoRes.setReviewCount(reviewCount);
        stadiumDetailInfoRes.setReviewList(reviews);
        stadiumDetailInfoRes.setStadiumAvgRate(stadiumAvgRate);

        return stadiumDetailInfoRes;
    }

    @Override
    public StadiumFacilityInfoRes findStadiumFacilityInfoById(Integer courtId) {
        return stadiumRepository.findStadiumFacilityInfoById(courtId);
    }

    @Override
    public String findStadiumNoticeById(Integer courtId) {
        return stadiumRepository.findStadiumNoticeById(courtId);
    }

    @Override
    public CourtInfoRes findCourtInfoByCourtReview(Integer courtReviewId) {
        return stadiumRepository.findCourtInfoByCourtReview(courtReviewId);
    }

    @Override
    public List<StadiumInfoRes> findAllStadiumPosition() {
        return stadiumRepository.findAllStadiumPosition();
    }

    @Override
    public StadiumInfoRes findStadiumPosition(Integer stadiumId) {
        return stadiumRepository.findStadiumPosition(stadiumId);
    }

    @Override
    public StadiumPagingInfo findPagingInfo() {
        return stadiumRepository.findPagingInfo();
    }

    @Override
    public List<Stadium> findAllStadiumInfo() {
        return stadiumRepository.findAllStadiumInfo();
    }

    @Override
    public List<Stadium> findAllStadiumInfoByLocation(StadiumCond stadiumCond) {
        return stadiumRepository.findAllStadiumInfoByLocation(stadiumCond);
    }

    @Override
    public StadiumFacilityInfoRes findStadiumFacilityInfoByStadiumId(Integer stadiumId) {
        return stadiumRepository.findStadiumFacilityInfoByStadiumId(stadiumId);
    }

    @Override
    public String findStadiumNoticeByStadiumId(Integer stadiumId) {
        return stadiumRepository.findStadiumNoticeByStadiumId(stadiumId);
    }

    @Override
    public List<StadiumInfoRes> findAllStadiumPositionByLocation(StadiumCond stadiumCond) {
        return stadiumRepository.findAllStadiumPositionByLocation(stadiumCond);
    }


}
