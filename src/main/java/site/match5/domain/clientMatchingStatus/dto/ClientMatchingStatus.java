package site.match5.domain.clientMatchingStatus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import lombok.*;
import site.match5.global.validation.annotation.CMatchingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class ClientMatchingStatus {
    @Positive
    private Integer userId;
    @CMatchingStatus
    private Integer status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate matchDay;
    @Positive
    private Integer matchTime;
}
