package site.match5.domain.court.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.court.dto.MatchedCourtInfoRes;

@Repository
@RequiredArgsConstructor
public class CourtRepositoryImpl implements CourtRepository {
    private final CourtMapper dao;

    @Override
    public MatchedCourtInfoRes findMatchedCourtInfo(Integer matchingHistoryId) {
        return dao.findMatchedCourtInfo(matchingHistoryId);
    }
}
