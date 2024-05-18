package site.match5.domain.alarm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AlarmRes {
    private Integer id;
    private Integer userId;
    private String content;
    private Integer status;
    private LocalDateTime createdAt;
    private Integer matchingHistoryId; //필드명과 다르게 되어있음
    private String type;

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        return this.createdAt.format(formatter);
    }
}
