package site.match5.domain.courtSchedule.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.courtSchedule.dto.CourtSchedule;
import site.match5.domain.courtSchedule.service.CourtScheduleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/court/schedule")
public class CourtScheduleController {
    private final CourtScheduleService courtScheduleService;

    // 해당 매칭의 구장 스케줄 조회
    @GetMapping("/{matchingHistoryId}")
    ResponseEntity<CourtSchedule> findCourtSchedule(@PathVariable Integer matchingHistoryId) {
        return ResponseEntity.ok(courtScheduleService.findScheduleByMatching(matchingHistoryId));
    }


//    // 구장 스케줄 예약 취소
//    @PatchMapping("/cancel/{courtScheduleId}")
//    ResponseEntity<String> cancelCourtSchedule(@PathVariable Integer courtScheduleId) {
//        courtScheduleService.cancelCourtSchedule(courtScheduleId);
//        return ResponseEntity.ok("success");
//    }


}
