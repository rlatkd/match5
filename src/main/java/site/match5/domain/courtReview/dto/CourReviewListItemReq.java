package site.match5.domain.courtReview.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CourReviewListItemReq {

    @Null
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer courtId;
    @NotBlank
    private String content;
    @NotNull
    @Range(min = 1, max = 5,message = "평점 범위는 1~5 입니다.")
    private Integer reviewRate;

    @Null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") // 직렬화 될때 원하는 포맷을 가지도록 설정 localdatetime 객체를 json으로 직렬화할때 ISO 8601 표준 형식으로하기 떄문에 "2024-05-10T02:12:09.889912" 이런식으로 보기 어렵게 나온다.
    private LocalDateTime createdAt;

    private String courtName;
    private String stadiumName;
}
