package site.match5.domain.matchedUser.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import site.match5.global.validation.annotation.Team;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchedUser {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer matchingHistoryId;
    @Team
    private String team;
}
