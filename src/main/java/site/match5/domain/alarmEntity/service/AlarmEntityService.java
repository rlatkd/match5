package site.match5.domain.alarmEntity.service;


import site.match5.domain.alarmEntity.entity.Alarm;

public interface AlarmEntityService {
    // 알람 생성
    void saveAlarm(Alarm alarm);
    // 알람 삭제
    void deleteAlarm(Integer matchingHistoryId);
    // 알림 읽음 상태 처리
    void updateAlarmRead(Integer alarmId);
}
