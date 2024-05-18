package site.match5.domain.matchingHistory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchingHistoryRes {
    private Integer id;
    private String StadiumName;
    private String courtName;
    private String selectedDate;
    private String startT;
    private String endT;
    private Integer status;
}
