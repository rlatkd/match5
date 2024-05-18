package site.match5.domain.alarmEntity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.alarmEntity.entity.Alarm;
import site.match5.domain.alarmEntity.repository.AlarmEntityRepository;

@Service
@RequiredArgsConstructor
public class AlarmEntityServiceImpl implements AlarmEntityService {
    private final AlarmEntityRepository alarmEntityRepository;
    @Transactional
    @Override
    public void saveAlarm(Alarm alarm) {
        alarmEntityRepository.saveAlarm(alarm);
    }

    @Transactional
    @Override
    public void deleteAlarm(Integer matchingHistoryId) {
        alarmEntityRepository.deleteAlarm(matchingHistoryId);
    }

    @Transactional
    @Override
    public void updateAlarmRead(Integer alarmId) {
        alarmEntityRepository.updateAlarmRead(alarmId);
    }
}
