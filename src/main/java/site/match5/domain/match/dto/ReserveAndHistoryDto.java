package site.match5.domain.match.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ReserveAndHistoryDto {
    private String location;
    private String selectedDate;
    private Integer selectedTime;
    private Integer hManagerId;
    private Integer aManagerId;


}
