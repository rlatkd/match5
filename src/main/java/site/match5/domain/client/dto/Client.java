package site.match5.domain.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Client {
    private Integer id;
    private Integer levelId;
    private String gender;
    private Integer age;
    private String nickname;
    private String kakaoId;
    private Integer currentExp;
    private Integer money;
    private Integer point;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;
    private Integer matchCount;
}
