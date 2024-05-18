package site.match5.domain.match.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.match5.domain.match.dto.MatchConditionReq;
import site.match5.domain.match.repository.MatchQMapper;

@Service
@RequiredArgsConstructor
public class MatchQService {

    private final MatchQMapper matchQMapper;

    // 매칭 조건을 파라미터로 받아서 매칭큐에 넣어주는 작업
    public void saveSelectedCondToMatchingQ(MatchConditionReq matchConditionReq){
        matchQMapper.saveSelectedCondToMatchingQ(matchConditionReq);
    }
}
