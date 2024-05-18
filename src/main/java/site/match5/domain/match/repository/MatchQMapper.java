package site.match5.domain.match.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.match5.domain.match.dto.MatchConditionReq;

@Mapper
public interface MatchQMapper {
    // 매칭 조건을 파라미터로 받아서 매칭큐에 넣어주는 작업
    void saveSelectedCondToMatchingQ(MatchConditionReq matchConditionReq);

}
