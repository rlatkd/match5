package site.match5.domain.clientMatchingStatus.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.clientMatchingStatus.dto.ClientMatchingStatus;

@Repository
@RequiredArgsConstructor
public class ClientMatchingStatusRepositoryImpl implements ClientMatchingStatusRepository {
    private final ClientMatchingStatusMapper dao;
    @Override
    public void updateUserMatchingStatus(Integer userId,ClientMatchingStatus clientMatchingStatus) {
        dao.updateUserMatchingStatus(userId,clientMatchingStatus);
    }

    @Override
    public ClientMatchingStatus findClientMatchingStatus(Integer userId) {
        return dao.findClientMatchingStatus(userId);
    }
}
