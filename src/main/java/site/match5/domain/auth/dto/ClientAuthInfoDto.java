package site.match5.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientAuthInfoDto {

    @NotBlank
    private String kakaoId;
    @NotBlank
    private String nickName;
    @NotBlank
    private String gender;
    @NotNull
    private int age;
    @NotNull
    private int levelId;
    @NotNull
    private int currentExp;

}
