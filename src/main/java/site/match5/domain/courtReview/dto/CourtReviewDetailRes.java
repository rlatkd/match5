package site.match5.domain.courtReview.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import site.match5.domain.courtReview.entity.CourtReview;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CourtReviewDetailRes {

    private Integer courtReviewId;
    private Integer userId;
    private Integer courtId;
    private String content;
    private Integer reviewRate;
    private String createdAt;
    private String courtName;
    private Float lng;
    private Float lat;
    private List<String> images;

    public CourtReviewDetailRes(Integer courtReviewId, Integer userId, Integer courtId, String content, Integer reviewRate, String createdAt) {
        this.courtReviewId = courtReviewId;
        this.userId = userId;
        this.courtId = courtId;
        this.content = content;
        this.reviewRate = reviewRate;
        this.createdAt = createdAt;
    }
}
