package site.match5.domain.courtSchedule.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.courtSchedule.dto.CourtSchedule;

@Repository
@RequiredArgsConstructor
public class CourtScheduleRepositoryImpl implements CourtScheduleRepository{
    private final CourtScheduleMapper dao;
    @Override
    public CourtSchedule findScheduleByMatching(Integer matchingHistoryId) {
        return dao.findScheduleByMatching(matchingHistoryId);
    }

    @Override
    public void cancelCourtSchedule(Integer courtScheduleId) {
        dao.cancelCourtSchedule(courtScheduleId);
    }
}
