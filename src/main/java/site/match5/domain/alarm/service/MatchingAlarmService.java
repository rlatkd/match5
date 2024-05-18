package site.match5.domain.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.alarm.dto.AlarmRes;
import site.match5.domain.alarm.repository.MatchingAlarmMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingAlarmService {
    private final MatchingAlarmMapper matchingAlarmMapper;

    // 매칭 완료 알람 데이터 생성
    @Transactional
    public void saveMatchingCompleteAlarm(Integer matchingHistoryId, List<Integer> userIdList) {
        matchingAlarmMapper.saveMatchingAlarm(matchingHistoryId,userIdList);
    }

    // 해당 유저의 모든 알람 데이터 반환
    public List<AlarmRes> getAllAlarmByUserId(int userId){
        return matchingAlarmMapper.getAllAlarmByUserId(userId);
    }


}
