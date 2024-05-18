package site.match5.domain.matchedUser.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.match5.domain.matchedUser.dto.AwayTeamInfo;
import site.match5.domain.matchedUser.dto.HomeTeamInfo;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoRes;
import site.match5.domain.matchedUser.dto.MatchedUser;

import java.util.List;

@Mapper
public interface MatchedUserMapper {

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
