package site.match5.domain.userReview.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import site.match5.global.validation.annotation.UserReviewRate;

@AllArgsConstructor
@Getter
@Setter
public class UserReview {
    @NotNull
    private Integer fromUserId;
    @NotNull
    private Integer toUserId;
    @NotNull
    private Integer matchingHistoryId;
    @UserReviewRate
    private Integer reviewRate;
}
