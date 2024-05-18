package site.match5.domain.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class MatchingWaitingListItem {
    private int userId;
    private Instant createdAt;
    private String selectedDate;    //ex) 2024-05-09
    private String location;    //ex) 성북구
}
