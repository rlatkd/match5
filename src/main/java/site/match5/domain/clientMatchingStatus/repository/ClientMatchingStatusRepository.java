package site.match5.domain.clientMatchingStatus.repository;

import site.match5.domain.clientMatchingStatus.dto.ClientMatchingStatus;

public interface ClientMatchingStatusRepository {
    // 유저 매칭 상태 업데이트
    void updateUserMatchingStatus(Integer userId,ClientMatchingStatus clientMatchingStatus);
    // 유저 매칭 상태 조회
    ClientMatchingStatus findClientMatchingStatus(Integer userId);
}
