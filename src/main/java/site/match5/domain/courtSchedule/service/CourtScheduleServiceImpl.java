package site.match5.domain.courtSchedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.match5.domain.courtSchedule.dto.CourtSchedule;
import site.match5.domain.courtSchedule.repository.CourtScheduleRepository;

@Service
@RequiredArgsConstructor
public class CourtScheduleServiceImpl implements CourtScheduleService{
    private final CourtScheduleRepository courtScheduleRepository;
    @Override
    public CourtSchedule findScheduleByMatching(Integer matchingHistoryId) {
        return courtScheduleRepository.findScheduleByMatching(matchingHistoryId);
    }

    @Transactional
    @Override
    public void cancelCourtSchedule(Integer courtScheduleId) {
        courtScheduleRepository.cancelCourtSchedule(courtScheduleId);
    }
}
