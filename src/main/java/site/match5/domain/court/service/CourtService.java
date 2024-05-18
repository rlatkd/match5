package site.match5.domain.court.service;

import site.match5.domain.court.dto.MatchedCourtInfoRes;

public interface CourtService {

    // 단일 - 매칭 된 구장 정보 조회   [구장아이디,구장사진,구장이름,구장스케줄 날짜,스케줄 가격,시작시간,끝시간, 풋살장 위치 ]
    MatchedCourtInfoRes findMatchedCourtInfo(Integer matchingHistoryId);
}
