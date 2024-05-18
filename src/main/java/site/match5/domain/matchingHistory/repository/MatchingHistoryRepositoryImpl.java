package site.match5.domain.matchingHistory.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import site.match5.domain.matchingHistory.dto.*;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Slf4j
public class MatchingHistoryRepositoryImpl implements MatchingHistoryRepository {
    private final MatchingHistoryMapper dao;

    @Override
    public Integer checkMatchingHistoryExist(Integer matchingHistoryId) {
        return dao.checkMatchingHistoryExist(matchingHistoryId);
    }

    @Override
    public void increaseConfirmUserCount(Integer matchingHistoryId) {
        dao.increaseConfirmUserCount(matchingHistoryId);
    }

    @Override
    public Integer findConfirmUserCount(Integer matchingHistoryId) {
        return dao.findConfirmUserCount(matchingHistoryId);
    }


    @Override
    public void updateMatchingHistoryStatus(Integer matchingHistoryId,MatchingHistoryStatusReq matchingHistoryStatusReq) {
        dao.updateMatchingHistoryStatus(matchingHistoryId,matchingHistoryStatusReq);
    }

    @Override
    public void deleteMatchingHistory(Integer matchingHistoryId) {
        dao.deleteMatchingHistory(matchingHistoryId);
    }


    @Override
    public List<MatchingHistoryRes> findAllMatchingHistoryByUserId(Integer userId, Integer page, Integer size) {
        return dao.findAllMatchingHistoryByUserId(userId,page,size);
    }

    @Override
    public Integer checkUserInMatching(Integer userId, Integer matchingHistoryId) {
        return dao.checkUserInMatching(userId,matchingHistoryId);
    }


}
