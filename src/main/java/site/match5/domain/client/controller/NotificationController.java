//package site.match5.domain.client.controller;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//public class NotificationController {
//    @GetMapping(value = "notiTest")
//    public List<String> getNotiTest()
//    {
//        return Arrays.asList("알림 1", "알림 2", "알림 3"); //return (알림 content 리스트);
//    }
//
//    @GetMapping(value = "notification")
//    public List<String> getNotification()
//    {
//        //해당 userId의 매칭알람 테이블 데이터를 리스트에 담아서 리턴
//        return null; //return (알림 content 리스트);
//    }
//
//    @GetMapping(value="subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public SseEmitter subscribe(){ //서버가 구독을 요청하면 subscribe 함수를 실행해서 emitter를 할당
//        SseEmitter emitter = new SseEmitter();
//
//        //설정한 시간동안 서버에서 연결을 유지
//        emitter.onTimeout(emitter::complete);
//        emitter.onCompletion(emitter::complete);
//
//        //이벤트 발생 시, 이 emitter을 사용하여 메세지를 전송
//        return emitter;
//    }
//}
