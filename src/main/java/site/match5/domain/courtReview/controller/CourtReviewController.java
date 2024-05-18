package site.match5.domain.courtReview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.courtReview.dto.CourtReviewDetailRes;
import site.match5.domain.courtReview.dto.StadiumAllCourtReviewRes;
import site.match5.domain.courtReview.dto.UpdateCourtReviewReq;
import site.match5.domain.courtReview.entity.CourtReview;
import site.match5.domain.courtReview.service.CourtReviewService;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review/court")
public class CourtReviewController {

    private final CourtReviewService courtReviewService;


    // 단일 - 구장평가 생성  [ 프로시저 사용하기위해 Map 사용 ]
    @PostMapping("")
    ResponseEntity<String> saveCourtReview(@Valid @RequestBody CourtReview courtReview) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("v_user_id", courtReview.getUserId());
        paramMap.put("v_court_id", courtReview.getCourtId());
        paramMap.put("v_content", courtReview.getContent());
        paramMap.put("v_review_rate", courtReview.getReviewRate());
        courtReviewService.saveCourtReview(paramMap);
        return ResponseEntity.ok("success");
    }

    // 단일 - 구장평가 수정 [ 프로시저 사용하기위해 Map 사용 ]
    @PatchMapping("/{courtReviewId}")
    ResponseEntity<String> updateCourtReview( @PathVariable Integer courtReviewId,@Valid @RequestBody UpdateCourtReviewReq updateCourtReviewReq) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("v_court_review_id", courtReviewId);
        paramMap.put("v_content", updateCourtReviewReq.getContent());
        paramMap.put("v_review_rate", updateCourtReviewReq.getReviewRate());
        courtReviewService.updateCourtReviewById(paramMap);
        return ResponseEntity.ok("success");
    }

    // 단일 - 구장평가 삭제 [ 프로시저 사용하기위해 Map 사용 ]
    @DeleteMapping("/{courtReviewId}")
    ResponseEntity<String> deleteCourtReviewById(@PathVariable Integer courtReviewId) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("v_court_review_id", courtReviewId);
        courtReviewService.deleteCourtReviewById(paramMap);
        return ResponseEntity.ok("success");
    }
//    // 구장평가 조회 [ 구장평가 정보 ]
//    @GetMapping("/{courtReviewId}")
//    ResponseEntity<CourtReview> findCourtReviewById(@PathVariable Integer courtReviewId) {
//        return ResponseEntity.ok(courtReviewService.findCourtReviewById(courtReviewId));
//    }

//    // 해당 유저가 작성한 구장 평가들 조회
//    @GetMapping("/all/{userId}")
//    ResponseEntity<List<CourtReview>> findAllCourtReviewByUserId(@PathVariable Integer userId) {
//        return ResponseEntity.ok(courtReviewService.findAllCourtReviewByUserId(userId));
//    }



//    // 해당 풋살장이 소유한 구장 평가들 조회
//    @GetMapping("/stadium/{stadiumId}")
//    ResponseEntity<List<StadiumAllCourtReviewRes>> findAllCourtReviewByStadiumId(@PathVariable Integer stadiumId) {
//        return ResponseEntity.ok(courtReviewService.findAllCourtReviewByStadiumId(stadiumId));
//    }
//
//    // 해당 풋살장이 소유한 구장의 평균 리뷰 점수들의 평균 조회
//    @GetMapping("/stadium/{stadiumId}/avg")
//    ResponseEntity<Float> findAvgCourtReviewRateByStadiumId(@PathVariable Integer stadiumId) {
//        return ResponseEntity.ok(courtReviewService.findAvgCourtReviewRateByStadiumId(stadiumId));
//    }
//
//    // 해당 풋살장이 소유한 구장의 모든 리뷰 개수 조회
//    @GetMapping("/stadium/{stadiumId}/reviewCount")
//    ResponseEntity<Integer> findStadiumTotalReviewCount(@PathVariable Integer stadiumId) {
//        return ResponseEntity.ok(courtReviewService.findStadiumTotalReviewCount(stadiumId));
//    }

    // 구장평가 상세 조회   [ 구장평가 정보 + 구장 사진,구장 이름,위도,경도 ]
    @GetMapping("/detail/{courtReviewId}")
    ResponseEntity<CourtReviewDetailRes> findCourtReviewDetailById(@PathVariable Integer courtReviewId) {
        return ResponseEntity.ok(courtReviewService.findCourtReviewDetailById(courtReviewId));
    }

//    // 유저가 작성한 모든 구장평가 상세 조회   [ 구장평가 정보 + 구장 사진,구장 이름,위도,경도 ]
//    @GetMapping("/detail/all/{userId}")
//    ResponseEntity<List<CourtReviewDetailRes>> findAllCourtReviewDetailById(@PathVariable Integer userId) {
//        return ResponseEntity.ok(courtReviewService.findAllCourtReviewDetailById(userId));
//    }

    // 유저가 작성한 모든 구장평가 상세 조회  + 페이징  [ 구장평가 정보 + 구장 사진,구장 이름,위도,경도 ]
    @GetMapping("/detail/all/{userId}/{page}/{size}")
    ResponseEntity<List<CourtReviewDetailRes>> findAllCourtReviewDetailByIdPaging(@PathVariable Integer userId, @PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(courtReviewService.findAllCourtReviewDetailByIdPaging(userId,page,size));
    }



}
