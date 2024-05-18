package site.match5.domain.matchedUser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.matchedUser.dto.AwayTeamInfo;
import site.match5.domain.matchedUser.dto.HomeTeamInfo;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoRes;
import site.match5.domain.matchedUser.dto.MatchedUser;
import site.match5.domain.matchedUser.repository.MatchedUserRepository;
import site.match5.domain.matchingHistory.service.MatchingHistoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchedServiceImpl implements MatchedUserService {
    private final MatchedUserRepository matchedUserRepository;

    @Transactional
    @Override
    public void saveMatchedUser(MatchedUser matchedUser) {
        matchedUserRepository.saveMatchedUser(matchedUser);
    }

    @Override
    public List<MatchedUser> findMatchedUsers(Integer matchingHistoryId) {
        return matchedUserRepository.findMatchedUsers(matchingHistoryId);
    }

    @Override
    public MatchedTeamInfoRes findMatchedUsersDetail(Integer matchingHistoryId) {
        AwayTeamInfo aTeam = findMatchedUsersATeam(matchingHistoryId);
        HomeTeamInfo hTeam = findMatchedUsersHTeam(matchingHistoryId);
        return new MatchedTeamInfoRes(hTeam.getHomeTeamUsersInfo(), aTeam.getAwayTeamUsersInfo(), hTeam.getHomeTeamManagerId(), aTeam.getAwayTeamManagerId());
    }

    @Transactional
    @Override
    public void deleteMatchedUser(Integer matchingHistoryId) {
        matchedUserRepository.deleteMatchedUser(matchingHistoryId);
    }

    @Override
    public HomeTeamInfo findMatchedUsersHTeam(Integer matchingHistoryId) {
        return matchedUserRepository.findMatchedUsersHTeam(matchingHistoryId);
    }

    @Override
    public AwayTeamInfo findMatchedUsersATeam(Integer matchingHistoryId) {
        return matchedUserRepository.findMatchedUsersATeam(matchingHistoryId);
    }
}
