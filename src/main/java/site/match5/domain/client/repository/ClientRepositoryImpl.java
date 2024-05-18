package site.match5.domain.client.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.client.dto.Client;
import site.match5.domain.client.dto.UpdateExpReq;
import site.match5.domain.client.dto.UpdateMoneyReq;
import site.match5.domain.client.dto.UpdatePointReq;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final UserMapper dao;

    // 단일 - 유저 경험치 업데이트  [ 레벨 등급에 대한 변화는 트리거로 처리 ]
    @Override
    public void updateLevelExp(Integer userId, UpdateExpReq updateExpReq) {
        dao.updateLevelExp(userId, updateExpReq);
    }

    // 단일 - 유저 포인트 업데이트
    @Override
    public void updatePointExp(Integer userId, UpdatePointReq updatePointReq) {
        dao.updatePointExp(userId, updatePointReq);
    }

    @Override
    public void updateMoneyExp(Integer userId, UpdateMoneyReq updatePointReq) {
        dao.updateMoneyExp(userId, updatePointReq);
    }

    @Override
    public Client findClient(Integer userId) {
        return dao.findClient(userId);
    }
}
