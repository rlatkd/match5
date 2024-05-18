package site.match5.domain.court.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MatchedCourtInfoRes {
    private Integer stadiumId;
    private Integer courtId;
    private String name;
    private List<String> images;
    private LocalDateTime scheduleDate;
    private String startT;
    private String endT;
    private Integer productPrice;
    private String lat;
    private String lng;

}
