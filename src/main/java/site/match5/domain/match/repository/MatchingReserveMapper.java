package site.match5.domain.match.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MatchingReserveMapper {
    /*
    선택된 날짜,시간,지역에서 예약 가능한 가장 리뷰평가가 좋은 풋살장이 가진 구장중 가장 리뷰 평가가 좋은 구장의 스케줄을
    예약처리하고, 매칭히스토리 데이터를 생성한다, 생성 후 매칭큐에서 해당 인원을 제거한다.
     */
    void matchingReserve(Map<String,Object> paramMap);

//    매칭된 유저는 매칭대기큐에서 삭제
    void deleteMatchedUserFromQ(List<Integer> userIdList);

}
