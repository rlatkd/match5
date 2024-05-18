package site.match5.domain.matchingQ.repository;

import org.apache.ibatis.annotations.Mapper;
import site.match5.domain.matchingQ.dto.MatchingQ;

@Mapper
public interface MatchingQMapper {
    // 단일 - 매칭큐 데이터 생성
    void saveMatchingQ(MatchingQ matchingQ);
}
