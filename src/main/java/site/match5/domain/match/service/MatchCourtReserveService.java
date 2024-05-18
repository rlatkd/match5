package site.match5.domain.match.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.match5.domain.match.dto.ReserveAndHistoryDto;
import site.match5.domain.match.repository.MatchingReserveMapper;
import site.match5.domain.matchingHistory.repository.MatchingHistoryMapper;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchCourtReserveService {

    private final MatchingReserveMapper matchingReserveMapper;
    private final MatchingHistoryMapper matchingHistoryMapper;

    int courtReserveAndCreateMatchingHistory(ReserveAndHistoryDto reserveAndHistoryDto) {
        log.info(" 구장 예약 및 매칭 히스토리 생성  ");// 구장 예약 및 매칭 히스토리 생성
        HashMap<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("v_location",reserveAndHistoryDto.getLocation());
        paramMap.put("v_selected_date", Timestamp.valueOf(reserveAndHistoryDto.getSelectedDate()));
//        paramMap.put("v_selected_date", "2024-05-24 00:00:00");
        paramMap.put("v_selected_time",reserveAndHistoryDto.getSelectedTime());
        paramMap.put("v_home_manager_id",reserveAndHistoryDto.getHManagerId());
        paramMap.put("v_away_manager_id",reserveAndHistoryDto.getAManagerId());
        paramMap.put("success_row",0);
        paramMap.put("matching_history_id",0);
        log.info("paramMap : {}", paramMap);
        matchingReserveMapper.matchingReserve(paramMap);

        log.info("해당 인원으로 스케줄 예약 성공 여부 {} , 예약 후 생성된 매칭 히스토리 아이디 {}", paramMap.get("success_row"), paramMap.get("matching_history_id"));

        int successRow = (Integer) paramMap.get("success_row");
        if (successRow == 0) {
            return -1;
        } else {
            int matchingHistoryId = (int) paramMap.get("matching_history_id");
            return  matchingHistoryId;
        }
    }

}
