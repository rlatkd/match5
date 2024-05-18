package site.match5.domain.client.service;

import site.match5.domain.client.dto.Client;
import site.match5.domain.client.dto.UpdateExpReq;
import site.match5.domain.client.dto.UpdateMoneyReq;
import site.match5.domain.client.dto.UpdatePointReq;

public interface ClientService {
    //단일 - 유저 경험치 업데이트  [ 레벨 등급에 대한 변화는 트리거로 처리 ]
    void updateLevelExp(Integer userId,UpdateExpReq updateExpReq);

    //단일 - 유저 포인트 업데이트
    void updatePointExp(Integer userId, UpdatePointReq updatePointReq);

    // 단일 - 유저 돈 업데이트
    void updateMoneyExp(Integer userId, UpdateMoneyReq updatePointReq);
    // 단일 - 유저 조회
    Client findClient(Integer userId);

}
