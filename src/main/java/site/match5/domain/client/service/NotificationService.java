//package site.match5.domain.client.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class NotificationService {
//    //이벤트 발생시 SseEmitter를 통해 메시지를 전송
//    private final Map<Long, SseEmitter> emitters = new HashMap<>(); //{id값: emitter, ...}
//
//    public SseEmitter addEmitter(Long userId, SseEmitter emitter){
//        emitters.put(userId, emitter);
//        return emitter;
//    }
//    public void sendNotification(Long userId, String message){
//        SseEmitter emitter = emitters.get(userId); //emitters에서 해당 유저의 emitter를 찾아서 그걸 통해 메세지를 보낸다
//        if(emitter != null){ //emitter있으면
//            try {
//                //"notification"이라는 이름의 이벤트를 생성하고, 이 이벤트에 message라는 데이터를 포함시켜 클라이언트에 전송
//                //클라이언트에서는 이 이벤트를 받아서 해당 메시지에 따라 특정 동작을 수행하거나 사용자에게 알림을 표시할 수 있다
//                emitter.send(SseEmitter.event().name("notification").data(message));
//            } catch (IOException e) {
//                emitters.remove(userId); // 연결이 끊어졌을 경우 제거
//            }
//        }
//    }
//
//}
