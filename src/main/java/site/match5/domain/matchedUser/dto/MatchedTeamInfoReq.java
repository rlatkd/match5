package site.match5.domain.matchedUser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MatchedTeamInfoReq {
    private List<Integer> hTeamUsersId;
    private List<Integer> aTeamUsersId;
}
