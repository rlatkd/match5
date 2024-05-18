package site.match5.domain.matchingQ.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import site.match5.global.validation.annotation.IsManager;
import site.match5.global.validation.annotation.IsOutdoor;
import site.match5.global.validation.annotation.Location;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchingQ {
    @NotNull
    private Integer userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate selectedDate; // 시간 이후로 저장안할꺼면 localdatetime 이 아닌 이걸써야한다.
    @NotNull
    @Range(min = 0, max = 24)
    private Integer selectedTime;
    @Location
    private String location;
    @IsManager
    private Integer isManager;
    @IsOutdoor
    private Integer isOutdoor;
}
