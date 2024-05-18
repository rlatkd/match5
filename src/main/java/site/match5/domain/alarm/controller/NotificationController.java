package site.match5.domain.alarm.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.match5.domain.alarm.service.NotificationService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;
//    public static Map<String, List<SseEmitter>> sseEmitters = new ConcurrentHashMap<String, List<SseEmitter>>();
    public static final ConcurrentHashMap<String, List<SseEmitter>> sseEmitters = new ConcurrentHashMap<>();
    ///subscribe/{userId}
    @GetMapping(value="/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE) //eventSource가 서버와 연결하는 endpoint
    public SseEmitter subscribe(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        Object idObj = session.getAttribute("authenticatedId");
        Object userId2 = session.getAttribute("userId");
        log.info("유저아이디 : {}",userId2);
        String userId = null;
        if (idObj instanceof String) {
            userId = (String) idObj; }

        System.out.println(userId+": userId received, subscribe method run");
        SseEmitter sseEmitter = notificationService.subscribe(userId); //카카오ID - {sessionId, ...} 연결
//        notificationService.notifyMessage(userId); >> 이래서 돔 로드 (subscribe)할 때마다 함수 호출됐던 것
        return  sseEmitter;
    }


    @PostMapping("/notify-message/{userId}") //이 주소로 요청이 들어오면 notifyMessage가 실행되며 메세지가 전달된다.
    public ResponseEntity<String> notifyMessage(@PathVariable String userId) throws IOException {
        notificationService.notifyMessage(userId);
        System.out.println("notify-message로 post요청 >> notifyMessage 실행");
        return ResponseEntity.status(HttpStatus.OK).body("Notification sent successfully");
    }

    @PostMapping("/notify-cancel/{userId}") //이 주소로 요청이 들어오면 notifyMessage가 실행되며 메세지가 전달된다.
    public ResponseEntity<String> notifyCancel(@PathVariable String userId) throws IOException {
        notificationService.notifyCancel(userId);
        System.out.println("notify-Cancel method run");
        return ResponseEntity.status(HttpStatus.OK).body("Notification-Cancel sent successfully");

    }

}

// emitter 등록
// 사용자가 '로그인'을 하면, 해당 사용자를 sseEmitters 에 등록되도록
// 자바스크립트에서 fetch 또는 EventSource를 사용하여 서버의 SSE 엔드포인트에 접근할 때 이 URL을 사용
// 클라이언트가 /subscribe/{userId} 엔드포인트를 호출하여 SSE를 구독하면, 해당 사용자의 SSE 연결을 설정하고,
// 이후에는 해당 사용자에게 알림을 보낼 수 있도록 준비