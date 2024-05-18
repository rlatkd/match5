package site.match5.domain.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.client.dto.Client;
import site.match5.domain.client.dto.UpdateExpReq;
import site.match5.domain.client.dto.UpdateMoneyReq;
import site.match5.domain.client.dto.UpdatePointReq;
import site.match5.domain.client.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    // 단일 - 유저 경험치 업데이트  [ 레벨 등급에 대한 변화는 트리거로 처리 ]
    @Override
    @Transactional
    public void updateLevelExp(Integer userId, UpdateExpReq updateExpReq) {
        clientRepository.updateLevelExp(userId, updateExpReq);
    }

    // 단일 - 유저 포인트 업데이트
    @Override
    @Transactional
    public void updatePointExp(Integer userId, UpdatePointReq updatePointReq) {
        clientRepository.updatePointExp(userId, updatePointReq);
    }

    @Override
    @Transactional
    public void updateMoneyExp(Integer userId, UpdateMoneyReq updatePointReq) {
        clientRepository.updateMoneyExp(userId, updatePointReq);
    }

    @Override
    public Client findClient(Integer userId) {
        return clientRepository.findClient(userId);
    }
}
