package site.match5.domain.auth.repository;


import org.apache.ibatis.annotations.Mapper;
import site.match5.domain.auth.dto.ClientAuthInfoDto;
import site.match5.domain.auth.dto.ClientAuthInfoDto2;

@Mapper
public interface AuthMapper {

    String getKakaoId(String kakaoId);
//    void insertClientAuthInfo(ClientAuthInfoDto clientAuthInfoDto);
    void insertClientAuthInfo2(ClientAuthInfoDto2 clientAuthInfoDto2);
    void insertClientMatchingStatus(int userId);
    int getUserIdByKakaoId(String kakaoId);
}
