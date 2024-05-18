package site.match5.domain.matchedUser.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MatchedUserInfo {
    private Integer id;
    private Integer levelId;
    private Integer age;
    private String nickname;
    private String imageUrl;
    private Integer matchCount;
}
