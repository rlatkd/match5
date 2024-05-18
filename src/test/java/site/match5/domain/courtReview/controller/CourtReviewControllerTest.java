//package site.match5.domain.courtReview.controller;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import site.match5.domain.courtReview.dto.UpdateCourtReviewReq;
//import site.match5.domain.courtReview.entity.CourtReview;
//import site.match5.domain.courtReview.service.CourtReviewService;
//import site.match5.global.exception.customException.BusinessException;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//@DisplayName("구장평가 api 테스트")
//class courtReviewServiceTest {
//
//    @Autowired
//    CourtReviewService courtReviewService;
//
//    @Test
//    @DisplayName("구장평가 수정")
//    void modifyCourtReview() {
//        //given
//        CourtReview courtReview = new CourtReview(1, 1, "리뷰내용", 1);
//        CourtReview savedCourtReview = courtReviewService.addCourtReview(courtReview); // 구장평가 생성 테스트도 자동으로 된다.
//        //when
//        UpdateCourtReviewReq updateCourtReviewReq = new UpdateCourtReviewReq("리뷰수정테스트",2);
//        courtReviewService.updateCourtReviewById(savedCourtReview.getId(), updateCourtReviewReq);
//        //then
//        CourtReview findCourtReview = courtReviewService.findCourtReviewById(savedCourtReview.getId()); // 구장평가 한개 조회 테스트도 자동으로 된다.
//        assertThat(findCourtReview.getContent()).isEqualTo(updateCourtReviewReq.getContent());
//        assertThat(findCourtReview.getReviewRate()).isEqualTo(updateCourtReviewReq.getReviewRate());
//    }
//
//    @Test
//    @DisplayName("구장평가 삭제")
//    void removeCourtReviewById() {
//        //given
//        CourtReview courtReview = new CourtReview(1, 1, "리뷰내용", 1);
//        CourtReview savedCourtReview = courtReviewService.addCourtReview(courtReview);
//
//        //when
//        courtReviewService.deleteCourtReviewById(savedCourtReview.getId());
//
//        //then
//        assertThatThrownBy(() -> courtReviewService.findCourtReviewById(savedCourtReview.getId()))
//                .isInstanceOf(BusinessException.class);
//    }
//
//    @Test
//    @DisplayName("구장평가 전체 조회 [사용자 아이디 사용]")
//    void getAllCourtReviewByUserId() {
//        //given
//        int userId = 1;
//        CourtReview courtReview1 = new CourtReview(userId, 1, "리뷰내용1", 1);
//        CourtReview courtReview2 = new CourtReview(userId, 1, "리뷰내용2", 2);
//        CourtReview courtReview3 = new CourtReview(userId, 1, "리뷰내용3", 3);
//        CourtReview courtReview4 = new CourtReview(userId, 1, "리뷰내용4", 5);
//        CourtReview savedCourtReview1 = courtReviewService.addCourtReview(courtReview1);
//        CourtReview savedCourtReview2 = courtReviewService.addCourtReview(courtReview2);
//        CourtReview savedCourtReview3 = courtReviewService.addCourtReview(courtReview3);
//        CourtReview savedCourtReview4 = courtReviewService.addCourtReview(courtReview4);
//
//        //when
//        List<CourtReview> courReviewList = courtReviewService.findAllCourtReviewByUserId(userId);
//
//        //then
//        assertThat(courReviewList).contains(savedCourtReview1, savedCourtReview2, savedCourtReview3, savedCourtReview4);
//
//    }
//}