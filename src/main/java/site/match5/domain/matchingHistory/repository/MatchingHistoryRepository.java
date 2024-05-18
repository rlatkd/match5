package site.match5.domain.matchingHistory.repository;

import org.apache.ibatis.annotations.Param;
import site.match5.domain.matchingHistory.dto.*;

import java.util.List;

public interface MatchingHistoryRepository {


    // 매칭 히스토리 아이디로 해당 매칭히스토리 데이터가 존재하는지 확인
    Integer checkMatchingHistoryExist(Integer matchingHistoryId);

    // 단일 - 매칭 확정 인원 증가
    void increaseConfirmUserCount (Integer matchingHistoryId);
    // 단일 - 현재 매칭 확정 인원 조회
    Integer findConfirmUserCount(Integer matchingHistoryId);

    // 매칭 히스토리 상태를 변경한다.
    void updateMatchingHistoryStatus(Integer matchingHistoryId,MatchingHistoryStatusReq matchingHistoryStatusReq);

    void deleteMatchingHistory(Integer matchingHistoryId);
    //12 - 내 매칭이력 조회
    List<MatchingHistoryRes> findAllMatchingHistoryByUserId(Integer userId, Integer start, Integer end);
    // 단일 - 해당 매칭에 속해있는 유저인지 확인
    Integer checkUserInMatching(Integer userId,Integer matchingHistoryId);



}

