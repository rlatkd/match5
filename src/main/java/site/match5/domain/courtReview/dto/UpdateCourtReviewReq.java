package site.match5.domain.courtReview.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCourtReviewReq {
    @NotBlank
    private String content;
    @NotNull
    @Range(min = 1, max = 5)
    private Integer reviewRate;
}
