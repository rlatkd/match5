package site.match5.domain.alarm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.match5.domain.alarm.dto.AlarmRes;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface MatchingAlarmMapper {
    // 매칭이 잡혔다는 알람 데이터 생성
    void saveMatchingAlarm(@Param("matchingHistoryId") Integer matchingHistoryId,@Param("userIdList") List<Integer> userIdList);

    // 해당 유저의 모든 알람 데이터 반환
    List<AlarmRes> getAllAlarmByUserId(int userId);
}
