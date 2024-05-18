package site.match5.domain.matchedUser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchedTeamInfoRes {
    private List<MatchedUserInfo> hTeamUsersInfo;
    private List<MatchedUserInfo> aTeamUsersInfo;
    private Integer hteamManagerId;
    private Integer ateamManagerId;
}
