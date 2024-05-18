package site.match5.domain.court.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.match5.domain.court.dto.MatchedCourtInfoRes;
import site.match5.domain.court.service.CourtService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/court")
public class CourtController {
    private final CourtService courtService;

    // 단일 - 매칭 된 구장 정보 조회   [풋살장아이디,구장아이디,구장사진,구장이름,구장스케줄 날짜,스케줄 가격,시작시간,끝시간, 풋살장 위치 ]
    @GetMapping("/{matchingHistoryId}")
    ResponseEntity<MatchedCourtInfoRes> findMatchedCourtInfo(@PathVariable Integer matchingHistoryId) {
        return ResponseEntity.ok(courtService.findMatchedCourtInfo(matchingHistoryId));
    }


}
