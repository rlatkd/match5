package site.match5.domain.client.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.match5.domain.client.dto.Client;
import site.match5.domain.client.dto.UpdateExpReq;
import site.match5.domain.client.dto.UpdateMoneyReq;
import site.match5.domain.client.dto.UpdatePointReq;

@Mapper
public interface UserMapper {
    // 단일 - 유저 경험치 업데이트  [ 레벨 등급에 대한 변화는 트리거로 처리 ]
    void updateLevelExp(@Param("userId") Integer userId, @Param("updateExpReq") UpdateExpReq updateExpReq);

    //단일 - 유저 포인트 업데이트
    void updatePointExp(@Param("userId")  Integer userId, @Param("updatePointReq") UpdatePointReq updatePointReq);

    // 단일 - 유저 돈 업데이트
    void updateMoneyExp(@Param("userId")  Integer userId, @Param("updateMoneyReq") UpdateMoneyReq updateMoneyReq);

    // 단일 - 유저 조회
    Client findClient(Integer userId);
}
