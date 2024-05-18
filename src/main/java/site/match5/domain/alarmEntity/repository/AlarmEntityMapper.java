package site.match5.domain.alarmEntity.repository;

import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.alarmEntity.entity.Alarm;

@Mapper
public interface AlarmEntityMapper {

    // 알람 생성
    void saveAlarm(Alarm alarm);

    // 알람 삭제
    void deleteAlarm(Integer matchingHistoryId);

    // 알림 읽음 상태 처리
    void updateAlarmRead(Integer alarmId);

}
