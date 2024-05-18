package site.match5.domain.matchingQ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.clientMatchingStatus.dto.ClientMatchingStatus;
import site.match5.domain.clientMatchingStatus.service.ClientMatchingStatusService;
import site.match5.domain.matchingQ.dto.MatchingQ;
import site.match5.domain.matchingQ.repository.MatchingQRepository;

@Service
@RequiredArgsConstructor
public class MatchingQServiceImpl   implements MatchingQService {
    private final MatchingQRepository matchingQRepository;
    private final ClientMatchingStatusService clientMatchingStatusService;
    @Override
    @Transactional
    public void saveMatchingQ(MatchingQ matchingQ) {
        matchingQRepository.saveMatchingQ(matchingQ);
        clientMatchingStatusService.updateUserMatchingStatus(matchingQ.getUserId(), ClientMatchingStatus.builder().status(1).build());
    }
}
