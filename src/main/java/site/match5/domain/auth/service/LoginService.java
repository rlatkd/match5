package site.match5.domain.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.match5.domain.auth.dto.KakaoUrlDto;

@Service
public class LoginService {


    @Value("${auth.kakao.rest-api-key}")
    private String kakaoRestApiKey;

    @Value("${auth.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    public KakaoUrlDto getUrl() {

        KakaoUrlDto kakaoUrlDto = new KakaoUrlDto();
        kakaoUrlDto.setUrl("https://kauth.kakao.com/oauth/authorize?client_id=" + kakaoRestApiKey + "&redirect_uri=" + kakaoRedirectUri + "&response_type=code");
        return kakaoUrlDto;

    }
}
