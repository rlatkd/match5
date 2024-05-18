package site.match5.domain.courtSchedule.service;

import site.match5.domain.courtSchedule.dto.CourtSchedule;

public interface CourtScheduleService {

    // 해당 매칭의 스케줄 조회
    CourtSchedule findScheduleByMatching(Integer matchingHistoryId);

    // 구장 스케줄 예약 취소
    void cancelCourtSchedule( Integer courtScheduleId);
}
