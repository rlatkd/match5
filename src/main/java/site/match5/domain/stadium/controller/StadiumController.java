package site.match5.domain.stadium.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.stadium.dto.*;
import site.match5.domain.stadium.service.StadiumService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stadium")
public class StadiumController {

    private final StadiumService stadiumService;

    // 구장 리뷰 작성용 구장 정보 가져오기 by 구장아이디  join 풋살장 [구장 이름,구장 사진, 풋살장 위도경도,
    @GetMapping("/court/{courtId}")
    ResponseEntity<CourtInfoRes> findCourtInfoById(@PathVariable Integer courtId) {
        return ResponseEntity.ok(stadiumService.findCourtInfoById(courtId));
    }

    // 전체 풋살장 조회 [ 풋살장 이름 , 풋살장 지역 ]
    @GetMapping("/all/{page}/{size}")
    ResponseEntity<List<StadiumNameAndLocRes>> findAllStadium( @PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(stadiumService.findAllStadium(page, size));
    }

    //    해당 지역 전체 풋살장 조회 by 지역 [ 풋살장 이름 , 풋살장 지역 ]
    @GetMapping("/all/{location}/{page}/{size}")
    ResponseEntity<List<StadiumNameAndLocRes>> findAllStadiumByLocation(@PathVariable String location,@PathVariable Integer page, @PathVariable Integer size) {
        return ResponseEntity.ok(stadiumService.findAllStadiumByLocation(location,page,size));
    }
    // 해당 풋살장의 모든 구장 사진, 풋살장 이름, 풋살장 위도경도
    @GetMapping("/{stadiumId}/info")
    ResponseEntity<StadiumInfoRes> findStadiumInfo(@PathVariable Integer stadiumId) {
        return ResponseEntity.ok(stadiumService.findStadiumInfo(stadiumId));
    }

    // 해당 풋살장 상세 정보 by 풋살장 id  [ 해당 풋살장의 모든 구장 사진, 풋살장 이름, 풋살장 위도경도, 풋살장 평균 평가점수,리뷰참여자수 , 해당 풋살장 모든 구장 리뷰정보(평점,내용,닉네임,시간)
    @GetMapping("/{stadiumId}/infoDetail")
    ResponseEntity<StadiumDetailInfoRes> findStadiumDetailInfo(@PathVariable Integer stadiumId) {
        return ResponseEntity.ok(stadiumService.findStadiumDetailInfo(stadiumId));
    }


    // 풋살장 정보 조회 by courtId[ 주차,샤워,신발대여,화장실여부 반환  + 가로세로]
    @GetMapping("/{courtId}/facilityInfo")
    ResponseEntity<StadiumFacilityInfoRes> findStadiumFacilityInfoById(@PathVariable Integer courtId){
        return ResponseEntity.ok(stadiumService.findStadiumFacilityInfoById(courtId));
    };
    // 풋살장 특이사항 조회 by courtId [rental intro 반환]
    @GetMapping("/{courtId}/notice")
    ResponseEntity<String>  findStadiumNoticeById(@PathVariable Integer courtId){
        return ResponseEntity.ok(stadiumService.findStadiumNoticeById(courtId));
    }

    // 풋살장 정보 조회 by stadiumId[ 주차,샤워,신발대여,화장실여부 반환  + 가로세로]
    @GetMapping("/{stadiumId}/facilityInfo/byStadium")
    ResponseEntity<StadiumFacilityInfoRes> findStadiumFacilityInfoByStadiumId(@PathVariable Integer stadiumId){
        return ResponseEntity.ok(stadiumService.findStadiumFacilityInfoByStadiumId(stadiumId));
    };
    // 풋살장 특이사항 조회 by stadiumId [rental intro 반환]
    @GetMapping("/{stadiumId}/notice/byStadium")
    ResponseEntity<String>  findStadiumNoticeByStadiumId(@PathVariable Integer stadiumId){
        return ResponseEntity.ok(stadiumService.findStadiumNoticeByStadiumId(stadiumId));
    }


    // 모든 풋살장 위도 경도 조회
    @GetMapping("/all/latlng")
    ResponseEntity<List<StadiumInfoRes>> findAllStadiumPosition() {
        return ResponseEntity.ok(stadiumService.findAllStadiumPosition());
    }

    // 풋살장 위도 경도 조회 by 풋살장 아이디
     @GetMapping("/{stadiumId}/latlng")
    ResponseEntity<StadiumInfoRes> findStadiumPosition(@PathVariable Integer stadiumId) {
        return ResponseEntity.ok(stadiumService.findStadiumPosition(stadiumId));
    }

//
//    // 모든 풋살장 갯수, 총 페이지수
//    @GetMapping("/all/page")
//    ResponseEntity<StadiumPagingInfo> findPagingInfo(){
//        return ResponseEntity.ok(stadiumService.findPagingInfo());
//    }



    // 모든 풋살장 정보 조회
    @GetMapping("/all/info")
    ResponseEntity<List<Stadium>> findAllStadiumInfo() {
        return ResponseEntity.ok(stadiumService.findAllStadiumInfo());
    }

    // 해당 지역 모든 풋살장 정보 조회
    @GetMapping("/all/info/location")
    ResponseEntity<List<Stadium>> findAllStadiumInfoByLocation(@Valid @ModelAttribute StadiumCond stadiumCond) {
        return ResponseEntity.ok(stadiumService.findAllStadiumInfoByLocation(stadiumCond));
    }

}
