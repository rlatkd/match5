package site.match5.domain.alarmEntity.repository;

import site.match5.domain.alarmEntity.entity.Alarm;

public interface AlarmEntityRepository {
    // 알람 생성
    void saveAlarm(Alarm alarm);
    // 알람 삭제
    void deleteAlarm(Integer matchingHistoryId);

    // 알림 읽음 상태 처리
    void updateAlarmRead(Integer alarmId);
}
