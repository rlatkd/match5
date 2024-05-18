package site.match5.domain.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.client.dto.ClientInfoDto;
import site.match5.domain.client.repository.ClientMapper;

@Service
@AllArgsConstructor
public class MypageService {

    private final ClientMapper clientMapper;

    public ClientInfoDto getClientInfo(String kakaoId) {
        return clientMapper.getClientInfo(kakaoId);
    }

    public String getClientProfileImage(String kakaoId) {
        return clientMapper.getClientProfileImage(kakaoId);
    }

    @Transactional
    public void updateClientProfileImage(String kakaoId, String imageUrl) {
        clientMapper.updateClientProfileImage(kakaoId, imageUrl);
    }
}
