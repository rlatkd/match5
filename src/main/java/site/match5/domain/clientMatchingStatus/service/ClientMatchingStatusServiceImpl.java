package site.match5.domain.clientMatchingStatus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.clientMatchingStatus.dto.ClientMatchingStatus;
import site.match5.domain.clientMatchingStatus.repository.ClientMatchingStatusRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientMatchingStatusServiceImpl implements ClientMatchingStatusService {
    private final ClientMatchingStatusRepository clientMatchingStatusRepository;

    @Transactional
    @Override
    public void updateUserMatchingStatus(Integer userId,ClientMatchingStatus clientMatchingStatus) {
        log.info("유저매칭상태 변경 userId : {} ,ClientMatchingStatus : {} ",userId,clientMatchingStatus);
        clientMatchingStatusRepository.updateUserMatchingStatus(userId,clientMatchingStatus);
    }

    @Override
    public ClientMatchingStatus findClientMatchingStatus(Integer userId) {
        return clientMatchingStatusRepository.findClientMatchingStatus(userId);
    }
}
