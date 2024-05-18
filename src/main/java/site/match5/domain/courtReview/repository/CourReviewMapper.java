package site.match5.domain.courtReview.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.match5.domain.courtReview.dto.CourtReviewDetailRes;
import site.match5.domain.courtReview.dto.StadiumAllCourtReviewRes;
import site.match5.domain.courtReview.entity.CourtReview;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface CourReviewMapper {

    // 단일 - 구장평가 생성  [ 프로시저 사용하기위해 Map 사용 ]
    Integer saveCourtReview(Map<String,Object> paramMap);
    // 단일 - 구장평가 수정 [ 프로시저 사용하기위해 Map 사용 ]
    Integer updateCourtReviewById(Map<String,Object> paramMap);
    // 단일 - 구장평가 삭제 [ 프로시저 사용하기위해 Map 사용 ]
    Integer deleteCourtReviewById(Map<String,Object> paramMap);
    // 구장평가 조회 [ 구장평가 정보 ]
    Optional<CourtReview> findCourtReviewById(Integer courtReviewId);
    // 해당 유저가 작성한 구장 평가들 조회
    List<CourtReview> findAllCourtReviewByUserId(Integer userId);
    // 해당 풋살장이 소유한 구장 평가들 조회
    List<StadiumAllCourtReviewRes> findAllCourtReviewByStadiumId(Integer stadiumId);
    // 해당 풋살장이 소유한 구장의 평균 리뷰 점수들의 평균 조회
    Float findAvgCourtReviewRateByStadiumId(Integer stadiumId);
    // 해당 풋살장이 소유한 구장의 모든 리뷰 개수 조회
    Integer findStadiumTotalReviewCount(Integer stadiumId);
    // 구장평가 상세 조회   [ 구장평가 정보 + 구장 사진,구장 이름,위도,경도 ]
    CourtReviewDetailRes findCourtReviewDetailById(Integer courtReviewId);
    // 유저가 작성한 모든 구장평가 상세 조회   [ 구장평가 정보 + 구장 사진,구장 이름,위도,경도 ]
    List<CourtReviewDetailRes> findAllCourtReviewDetailById( Integer userId);

    // 유저가 작성한 모든 구장평가 상세 조회   [ 구장평가 정보 + 구장 사진,구장 이름,위도,경도 ]
    List<CourtReviewDetailRes> findAllCourtReviewDetailByIdPaging( Integer userId, Integer start,Integer end);

}
