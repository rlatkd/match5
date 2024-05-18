package site.match5.domain.courtReview.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StadiumAllCourtReviewRes {
    private int reviewRate;
    private String content;
    private String nickname;
    private String createdAt;
}
