package site.match5.domain.courtSchedule.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.match5.domain.courtSchedule.dto.CourtSchedule;

@Mapper
public interface CourtScheduleMapper {

    // 해당 매칭의 스케줄 조회
    CourtSchedule findScheduleByMatching(Integer matchingHistoryId);

    // 구장 스케줄 예약 취소
    void cancelCourtSchedule( Integer courtScheduleId);
}
