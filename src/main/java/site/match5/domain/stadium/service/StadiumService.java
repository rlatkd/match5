package site.match5.domain.stadium.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.match5.domain.stadium.dto.*;
import site.match5.domain.stadium.repository.StadiumRepository;

import java.util.List;

public interface StadiumService {
    // 구장 리뷰 작성용 구장 정보 가져오기 by 구장아이디  join 풋살장 [구장 이름,구장 사진, 풋살장 위도경도,
    CourtInfoRes findCourtInfoById(Integer courtId);
    // 전체 풋살장 조회 [ 풋살장 이름 , 풋살장 지역 ]
    List<StadiumNameAndLocRes> findAllStadium(Integer page, Integer size);
    // 해당 지역 전체 풋살장 조회 by 지역 [ 풋살장 이름 , 풋살장 지역 ]
    List<StadiumNameAndLocRes> findAllStadiumByLocation(String location,Integer page, Integer size);
    // 해당 풋살장의 모든 구장 사진, 풋살장 이름, 풋살장 위도경도
    StadiumInfoRes findStadiumInfo( Integer stadiumId) ;
    // 해당 풋살장 상세 정보 by 풋살장 id  [ 해당 풋살장의 모든 구장 사진, 풋살장 이름, 풋살장 위도경도, 풋살장 평균 평가점수,리뷰참여자수 , 해당 풋살장 모든 구장 리뷰정보(평점,내용,닉네임,시간)
    StadiumDetailInfoRes findStadiumDetailInfo(Integer stadiumId);

    // 풋살장 정보 조회 by courtId[ 주차,샤워,신발대여,화장실여부 반환 ]
    StadiumFacilityInfoRes findStadiumFacilityInfoById(Integer courtId);
    // 풋살장 특이사항 조회 by courtId[rental intro 반환]
    String findStadiumNoticeById(Integer courtId);

    // 내 구장 리뷰 상세정보확인용 구장 정보 가져오기 by 구장리뷰아이디  join 풋살장 [구장 이름,구장 사진, 풋살장 위도경도,
    CourtInfoRes findCourtInfoByCourtReview(Integer courtReviewId);

    // 모든 풋살장 위도 경도 조회
    List<StadiumInfoRes> findAllStadiumPosition();

    // 선택 지역 풋살장 위도 경도 조회

    // 풋살장 위도 경도 조회 by 풋살장 아이디
    StadiumInfoRes findStadiumPosition(Integer stadiumId);

    //페이징 정보 조회 ( 총 아이템 갯수, 총 페이지 갯수)
    StadiumPagingInfo findPagingInfo();

    // 모든 풋살장 정보 조회
    List<Stadium> findAllStadiumInfo();
    // 해당 지역 모든 풋살장 정보 조회
    List<Stadium> findAllStadiumInfoByLocation(StadiumCond stadiumCond);
    // 풋살장 정보 조회 by stadiumId[ 주차,샤워,신발대여,화장실여부 반환  + 가로세로]
    StadiumFacilityInfoRes findStadiumFacilityInfoByStadiumId(Integer stadiumId);

    // 풋살장 특이사항 조회 by stadiumId [rental intro 반환]
    String  findStadiumNoticeByStadiumId(Integer stadiumId);

    // 선택 지역 풋살장 위도 경도 조회
    List<StadiumInfoRes> findAllStadiumPositionByLocation( StadiumCond stadiumCond );
}
