package site.match5.domain.stadium.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewInfo {
    // 닉네임
    private String nickname;
    // 리뷰내용
    private String content;
    // 평점
    private Float avgRate;
    // 작성시간
    private LocalDateTime createdAt;

}
