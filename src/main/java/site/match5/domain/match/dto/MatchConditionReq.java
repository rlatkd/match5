package site.match5.domain.match.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchConditionReq {

    @NotNull
    private Integer userId;
    @NotBlank
    private String selectedDate;
    @NotNull
    private Integer selectedTime;
    @NotBlank
    private String location;
    @NotNull
    private Integer isManager;
    @NotNull
    private Integer isOutdoor;
}
