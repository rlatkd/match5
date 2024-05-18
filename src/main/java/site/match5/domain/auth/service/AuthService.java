package site.match5.domain.auth.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.match5.domain.auth.dto.KakaoIdDto;
import site.match5.domain.auth.dto.KakaoTokenDto;
import site.match5.domain.auth.repository.AuthMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AuthService {

    @Value("${auth.kakao.rest-api-key}")
    private String kakaoRestApiKey;

    @Value("${auth.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Autowired
    private AuthMapper authMapper;

    //카카오 액세스토큰 발급
    public KakaoTokenDto getKakaoAccessToken (String code) { //카카오토큰 가져오려면 code 필요

        KakaoTokenDto kakaoTokenDto = new KakaoTokenDto();
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";  //토큰발급용 카카오API

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 parameter stream을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + kakaoRestApiKey); //REST_API_KEY
            sb.append("&redirect_uri=" + kakaoRedirectUri); //REDIRECT_URI
            sb.append("&code=" + code);
            //System.out.println("이거보셈: " + sb.toString());
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            //int responseCode = conn.getResponseCode();
            //System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            //System.out.println("response body : " + result);

            //JSON parsing 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString(); //getToken
            //refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        kakaoTokenDto.setAccessToken(access_Token); //가져온 토큰 DTO에 주입
        //System.out.println("token : " + tokenDto.getAccessToken());
        return kakaoTokenDto;

    }

    //카카오 유저 정보 조회
    public KakaoIdDto getKakaoUserInfo(KakaoTokenDto kakaoTokenDto) {

        KakaoIdDto kakaoIdDto = new KakaoIdDto();
        String reqURL = "https://kapi.kakao.com/v2/user/me"; //카카오유저 조회용 카카오API

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + kakaoTokenDto.getAccessToken()); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            //int responseCode = conn.getResponseCode();
            //System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            //System.out.println("response body : " + result);

            //JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            String id = element.getAsJsonObject().get("id").getAsString(); //카카오ID
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString(); //카카오가입 email
            }

            //System.out.println("id : " + id);
            //System.out.println("email : " + email);
            kakaoIdDto.setKakaoId(id); //카카오ID DTO에 주입
            //System.out.println("authDto : " + authDto);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("[카카오 로그인]");
        return kakaoIdDto;

    }

    //우리 서비스에 이미 가입한 카카오 계정인지
    public String getAuthentication(KakaoIdDto kakaoIdDto) {
        String kakaoId = kakaoIdDto.getKakaoId();
        String existingKakaoId = authMapper.getKakaoId(kakaoId);
       return existingKakaoId;
    }

    //카카오 연결 끝
    public void closeKakaoAccess(KakaoTokenDto kakaoTokenDto) {

        String reqURL = "https://kapi.kakao.com/v1/user/unlink"; //카카오API 연결해제

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + kakaoTokenDto.getAccessToken()); //전송할 header 작성, access_token전송

            int responseCode = conn.getResponseCode();
            //System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            //System.out.println("response body : " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("[카카오 로직 끝]");
    }

}
