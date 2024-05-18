package site.match5.domain.matchedUser.repository;


import site.match5.domain.matchedUser.dto.AwayTeamInfo;
import site.match5.domain.matchedUser.dto.HomeTeamInfo;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoRes;
import site.match5.domain.matchedUser.dto.MatchedUser;

import java.util.List;

public interface MatchedUserRepository {

    // 단일 - 매칭된 유저 생성
    void saveMatchedUser(MatchedUser matchedUser);

    // 해당 매칭에 소속된 유저들 조회
    List<MatchedUser> findMatchedUsers(Integer matchingHistoryId);

    // 단일 - 해당 매칭에 소속된 데이터 삭제
    void deleteMatchedUser(Integer matchingHistoryId);

    // 단일 - 해당 매칭에 소속된 홈팀 정보 조회
    HomeTeamInfo findMatchedUsersHTeam(Integer matchingHistoryId);

    // 단일 - 해당 매칭에 소속된 어웨이팀 정보 조회
    AwayTeamInfo findMatchedUsersATeam(Integer matchingHistoryId);
}
