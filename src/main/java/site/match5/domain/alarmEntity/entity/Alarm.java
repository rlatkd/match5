package site.match5.domain.alarmEntity.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.match5.global.validation.annotation.AlarmType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alarm {
    @NotNull
    private Integer userId;
    @AlarmType
    private String type;

    private Integer matchingHistoryId; // null 가능

    public Alarm(Integer userId, String type) {
        this.userId = userId;
        this.type = type;
    }
}
