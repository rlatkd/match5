package site.match5.domain.alarmEntity.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.alarmEntity.entity.Alarm;

@Repository
@RequiredArgsConstructor
public class AlarmEntityRepositoryImpl implements AlarmEntityRepository {
    private final AlarmEntityMapper dao;
    @Override
    public void saveAlarm(Alarm alarm) {
        dao.saveAlarm(alarm);
    }

    @Override
    public void deleteAlarm(Integer matchingHistoryId) {
        dao.deleteAlarm(matchingHistoryId);
    }

    @Override
    public void updateAlarmRead(Integer alarmId) {
        dao.updateAlarmRead(alarmId);
    }
}
