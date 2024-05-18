package site.match5.domain.matchedUser.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AwayTeamInfo {
    private Integer AwayTeamManagerId;
    private List<MatchedUserInfo> AwayTeamUsersInfo;
}
