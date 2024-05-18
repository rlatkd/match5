package site.match5.domain.matchingHistory.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.match5.domain.matchingHistory.dto.*;

import java.util.List;

@Mapper
public interface MatchingHistoryMapper {


    // 매칭 히스토리 아이디로 해당 매칭히스토리 데이터가 존재하는지 확인
    Integer checkMatchingHistoryExist(Integer matchingHistoryId);
    // 단일 - 매칭 확정 인원 증가
    void increaseConfirmUserCount (Integer matchingHistoryId);
    // 단일 - 현재 매칭 확정 인원 조회
    Integer findConfirmUserCount(Integer matchingHistoryId);


    // 매칭 히스토리 상태를 변경한다.
    void updateMatchingHistoryStatus(@Param("matchingHistoryId") Integer matchingHistoryId,@Param("matchingHistoryStatusReq") MatchingHistoryStatusReq matchingHistoryStatusReq);

    void deleteMatchingHistory(Integer matchingHistoryId);

    //12 - 내 매칭이력 조회
    List<MatchingHistoryRes> findAllMatchingHistoryByUserId(@Param("userId") Integer userId, @Param("start") Integer start, @Param("end") Integer end);

    // 단일 - 해당 매칭에 속해있는 유저인지 확인
    Integer checkUserInMatching(@Param("userId") Integer userId,@Param("matchingHistoryId") Integer matchingHistoryId);


}
