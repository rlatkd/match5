package site.match5.domain.courtSchedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Getter
@Setter
public class CourtSchedule {
    private Integer id;
    private Integer courtId;
    private LocalDate scheduleDate;
    private String startT;
    private String endT;
    private Integer productPrice;
    private String isReserved;

}
