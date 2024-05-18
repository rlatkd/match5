package site.match5.domain.client.repository;


import org.apache.ibatis.annotations.Mapper;
import site.match5.domain.client.dto.ClientInfoDto;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientInfoDto getClientInfo(String kakaoId);
    String getClientProfileImage(String kakaoId);
    void updateClientProfileImage(String kakaoId, String imageUrl);
    List<String> getKakaoIdListFromUserIdList(List<Integer> userIdList);

}