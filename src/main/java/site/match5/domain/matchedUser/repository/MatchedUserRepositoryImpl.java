package site.match5.domain.matchedUser.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.matchedUser.dto.AwayTeamInfo;
import site.match5.domain.matchedUser.dto.HomeTeamInfo;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoRes;
import site.match5.domain.matchedUser.dto.MatchedUser;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchedUserRepositoryImpl implements MatchedUserRepository {
    private final MatchedUserMapper dao;
    @Override
    public void saveMatchedUser(MatchedUser matchedUser) {
        dao.saveMatchedUser(matchedUser);
    }

    @Override
    public List<MatchedUser> findMatchedUsers(Integer matchingHistoryId) {
        return dao.findMatchedUsers(matchingHistoryId);
    }

    @Override
    public void deleteMatchedUser(Integer matchingHistoryId) {
        dao.deleteMatchedUser(matchingHistoryId);
    }

    @Override
    public HomeTeamInfo findMatchedUsersHTeam(Integer matchingHistoryId) {
        return dao.findMatchedUsersHTeam(matchingHistoryId);
    }

    @Override
    public AwayTeamInfo findMatchedUsersATeam(Integer matchingHistoryId) {
        return dao.findMatchedUsersATeam(matchingHistoryId);
    }
}
