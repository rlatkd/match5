package site.match5.domain.clientMatchingStatus.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.match5.domain.clientMatchingStatus.dto.ClientMatchingStatus;

@Mapper
public interface ClientMatchingStatusMapper {
    // 유저 매칭 상태 업데이트
    void updateUserMatchingStatus(@Param("userId") Integer userId,@Param("clientMatchingStatus") ClientMatchingStatus clientMatchingStatus);
    // 유저 매칭 상태 조회
    ClientMatchingStatus findClientMatchingStatus(Integer userId);
}
