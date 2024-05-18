package site.match5.domain.matchingQ.service;

import site.match5.domain.matchingQ.dto.MatchingQ;

public interface MatchingQService {
    // 단일 - 매칭큐 데이터 생성
    void saveMatchingQ(MatchingQ matchingQ);
}
