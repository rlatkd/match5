package site.match5.domain.match.repository;

import org.apache.ibatis.annotations.Mapper;
import site.match5.domain.match.dto.MatchingQueueItem;

import java.time.Instant;
import java.util.List;

@Mapper
public interface PrepareDataMapper {

    // 첫 실행시 DB의 모든 데이터를 가져오는 sql문
    List<MatchingQueueItem> selectAll();

    // DB에서 새로 매칭 큐 테이블에 추가된 유저의 정보를 가져오는 쿼리문
    List<MatchingQueueItem> selectNewData(Instant createdAt);
}
