package site.match5.domain.matchedUser.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.match5.domain.matchedUser.dto.AwayTeamInfo;
import site.match5.domain.matchedUser.dto.HomeTeamInfo;
import site.match5.domain.matchedUser.dto.MatchedUser;
import site.match5.domain.matchedUser.service.MatchedUserService;
import site.match5.domain.matchedUser.dto.MatchedTeamInfoRes;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matched")
public class MatchedUserController {
    private final MatchedUserService matchedUserService;

    // 단일 - 매칭된 유저 생성
    @PostMapping("")
    ResponseEntity<String> saveMatchedUser(@RequestBody @Valid MatchedUser matchedUser) {
        matchedUserService.saveMatchedUser(matchedUser);
        return ResponseEntity.ok("success");
    }

    // 해당 매칭에 소속된 유저들 간단 조회
    @GetMapping("/users/{matchingHistoryId}")
    ResponseEntity<List<MatchedUser>> findMatchedUsers(@PathVariable("matchingHistoryId") Integer matchingHistoryId) {
        return ResponseEntity.ok(matchedUserService.findMatchedUsers(matchingHistoryId));
    }

    // 단일 - 해당 매칭에 소속된 유저들 상세정보 조회 [매칭된 유저들 상세정보 + 홈팀매니저 아이디 ,어웨이팀 매니저 아이디 ]
    @GetMapping("/users/detail/{matchingHistoryId}")
    ResponseEntity<MatchedTeamInfoRes> findMatchedUsersDetail(@PathVariable Integer matchingHistoryId) {
        return ResponseEntity.ok(matchedUserService.findMatchedUsersDetail(matchingHistoryId));
    }

    // 단일 - 해당 매칭에 소속된 매칭 유저 데이터 삭제
    @DeleteMapping("/users/delete/{matchingHistoryId}")
    ResponseEntity<String> deleteMatchedUser(@PathVariable Integer matchingHistoryId) {
        matchedUserService.deleteMatchedUser(matchingHistoryId);
        return ResponseEntity.ok("success");
    }


    // 단일 - 해당 매칭에 소속된 홈팀 정보 조회
    @GetMapping("/users/Hteam/{matchingHistoryId}")
    ResponseEntity<HomeTeamInfo> findMatchedUsersHTeam(@PathVariable Integer matchingHistoryId) {
        return ResponseEntity.ok(matchedUserService.findMatchedUsersHTeam(matchingHistoryId));
    }

    // 단일 - 해당 매칭에 소속된 어웨이팀 정보 조회
    @GetMapping("/users/Ateam/{matchingHistoryId}")
    ResponseEntity<AwayTeamInfo> findMatchedUsersATeam(@PathVariable Integer matchingHistoryId) {
        return ResponseEntity.ok(matchedUserService.findMatchedUsersATeam(matchingHistoryId));
    }


//    // 리스트 - 매칭된 유저들 생성 [ A팀, H팀 각각 유저리스트를 받아서 생성 ]
//    @GetMapping("")

}
