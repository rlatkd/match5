package site.match5.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAuthInfoDto2 {


    private int userId;

    private String kakaoId;

    private String nickName;

    private String gender;

    private int age;

    private int levelId;

    private int currentExp;

    public ClientAuthInfoDto2(String kakaoId, String nickName, String gender, int age, int levelId, int currentExp) {
        this.kakaoId = kakaoId;
        this.nickName = nickName;
        this.gender = gender;
        this.age = age;
        this.levelId = levelId;
        this.currentExp = currentExp;
    }

}
