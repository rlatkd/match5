package site.match5.domain.court.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.match5.domain.court.dto.MatchedCourtInfoRes;
import site.match5.domain.court.repository.CourtRepository;

@Service
@RequiredArgsConstructor
public class CourtServiceImpl  implements CourtService {
    private final CourtRepository courtRepository;

    @Override
    public MatchedCourtInfoRes findMatchedCourtInfo(Integer matchingHistoryId) {
        return courtRepository.findMatchedCourtInfo(matchingHistoryId);
    }
}
