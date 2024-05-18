package site.match5.domain.courtReview.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // @RequestBody를 통해 역직렬화하려면 기본생성자가 필요하다. ArgumentResolver가 httpmessageConverter를 통해 역직렬화 해준다.
@EqualsAndHashCode // 테스트에서 동일성(identity) 비교를 위해 추가.
public class CourtReview {

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

    private String nickname;

    @Null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") // 직렬화 될때 원하는 포맷을 가지도록 설정 localdatetime 객체를 json으로 직렬화할때 ISO 8601 표준 형식으로하기 떄문에 "2024-05-10T02:12:09.889912" 이런식으로 보기 어렵게 나온다.
    private LocalDateTime createdAt;

    public CourtReview(Integer userId, Integer courtId, String content, Integer reviewRate) {
        this.userId = userId;
        this.courtId = courtId;
        this.content = content;
        this.reviewRate = reviewRate;
    }
}
