package site.match5.domain.matchingQ.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.matchingQ.dto.MatchingQ;

@Repository
@RequiredArgsConstructor
public class MatchingQRepositoryImpl implements MatchingQRepository{
    private final MatchingQMapper dao;

    @Override
    public void saveMatchingQ(MatchingQ matchingQ) {
        dao.saveMatchingQ(matchingQ);
    }
}
