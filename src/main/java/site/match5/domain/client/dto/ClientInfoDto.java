package site.match5.domain.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientInfoDto {

    @NotBlank
    private String nickName;
    @NotNull
    private int age;
    @NotBlank
    private String gender;
    @NotNull
    private int levelId;
    @NotNull
    private int currentExp;
    @NotNull
    private int matchCount;
    @NotNull
    private int chargeAmount;
    @NotNull
    private int point;

}