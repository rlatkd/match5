package site.match5.domain.stadium.dto;

import lombok.Getter;
import lombok.Setter;
import site.match5.domain.courtReview.dto.StadiumAllCourtReviewRes;

import java.util.List;

@Getter
@Setter
public class StadiumDetailInfoRes {
    //해당 풋살장의 모든 구장 사진, 풋살장 이름, 풋살장 위도경도, 풋살장 평균 평가점수,리뷰참여자수 , 해당 풋살장 모든 구장 리뷰정보(평점,내용,닉네임,시간)
    private String name;
    private Float lat; // 위도
    private Float lng; //경도
    private List<String> images;
    private Float stadiumAvgRate;
    private Integer reviewCount;
    private List<StadiumAllCourtReviewRes> reviewList;
}
