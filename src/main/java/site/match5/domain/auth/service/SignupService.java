package site.match5.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.auth.dto.ClientAuthInfoDto;

import site.match5.domain.auth.dto.ClientAuthInfoDto2;
import site.match5.domain.auth.repository.AuthMapper;


@RequiredArgsConstructor
@Service
@Slf4j
public class SignupService {

    public final AuthMapper authMapper;

    @Transactional
    public void registClient(ClientAuthInfoDto clientAuthInfoDto) {
//        authMapper.insertClientAuthInfo(clientAuthInfoDto);
        ClientAuthInfoDto2 clientAuthInfoDto2 = new ClientAuthInfoDto2(clientAuthInfoDto.getKakaoId(), clientAuthInfoDto.getNickName(), clientAuthInfoDto.getGender(), clientAuthInfoDto.getAge(), clientAuthInfoDto.getLevelId(), clientAuthInfoDto.getCurrentExp());
        authMapper.insertClientAuthInfo2(clientAuthInfoDto2);
        authMapper.insertClientMatchingStatus(clientAuthInfoDto2.getUserId());
        log.info("userId : {}", clientAuthInfoDto2.getUserId());


    }

}
