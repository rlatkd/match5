package site.match5.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAndKaKao {
    private String kakaoId;
    private Integer userId;
}
