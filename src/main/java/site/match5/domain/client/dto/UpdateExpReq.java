package site.match5.domain.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.match5.global.validation.annotation.UserReviewRate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExpReq {
    @UserReviewRate // null 검사 + 값은 -1 ,0 , 1만 가능하다.
    private Integer exp;
}
