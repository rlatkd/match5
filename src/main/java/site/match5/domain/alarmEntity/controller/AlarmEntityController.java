package site.match5.domain.alarmEntity.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.alarmEntity.entity.Alarm;
import site.match5.domain.alarmEntity.service.AlarmEntityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
public class AlarmEntityController {
    private final AlarmEntityService alarmEntityService;

//    // 알람 생성
//    @PostMapping("")
//    ResponseEntity<String> saveAlarm(@Valid @RequestBody Alarm alarm) {
//        alarmEntityService.saveAlarm(alarm);
//        return ResponseEntity.ok("success");
//    }
//
//    // 해당 매칭 관련 알람 삭제
//    @DeleteMapping("/match/{matchingHistoryId}")
//    ResponseEntity<String> deleteAlarm(@PathVariable Integer matchingHistoryId) {
//        alarmEntityService.deleteAlarm(matchingHistoryId);
//        return ResponseEntity.ok("success");
//    }
//
//    // 알림 읽음 상태 처리
//    @PatchMapping("update/{alarmId}")
//    ResponseEntity<String> updateAlarmRead(@PathVariable Integer alarmId) {
//        alarmEntityService.updateAlarmRead(alarmId);
//        return ResponseEntity.ok("success");
//    }


}
