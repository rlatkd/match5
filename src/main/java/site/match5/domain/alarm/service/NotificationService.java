package site.match5.domain.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.match5.domain.alarm.controller.NotificationController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class NotificationService {

    // 메시지 알림
    public SseEmitter subscribe(String userId) throws IOException {
        //private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
        // 1. 현재 클라이언트를 위한 sseEmitter 객체 생성
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE); //연결이 최대한 오랫동안 유지되도록 타임아웃을 설정하는 값

        // 3. 저장
        List<SseEmitter> sseEmitters = NotificationController.sseEmitters.getOrDefault(userId, new CopyOnWriteArrayList<>());
        sseEmitters.add(sseEmitter);
        NotificationController.sseEmitters.put(userId, sseEmitters);

        // 2. 더미데이터 전송
        notifyConnect(userId);

        // 4. 연결 종료 처리
        sseEmitter.onCompletion(() -> NotificationController.sseEmitters.remove(userId));    // sseEmitter 연결이 완료될 경우
        sseEmitter.onTimeout(() -> NotificationController.sseEmitters.remove(userId));        // sseEmitter 연결에 타임아웃이 발생할 경우
        sseEmitter.onError((e) -> NotificationController.sseEmitters.remove(userId));        // sseEmitter 연결에 오류가 발생할 경우

        return sseEmitter;
    }

    // 메세지 알림
//    public void notifyMessage(String userId) throws IOException {
//        // 5. 매칭알림을 보낼 대상자 조회
//        //User user = userRepository.findByNickname(receiver);
//
//        if (NotificationController.sseEmitters.containsKey(userId)) {
//            List<SseEmitter> sseEmitterReceivers = NotificationController.sseEmitters.get(userId);
//            sseEmitterReceivers.forEach(sseEmitter -> {
//                int err = 1;
//                while (err == 1) {
//                    try {
//                        err = 0;
//                        sseEmitter.send(SseEmitter.event().name("matching-alarm").data("매칭이 완료되었습니다. 알림함을 확인해주세요"));
//                    } catch (Exception e) {
////                        System.out.println(e.getMessage());
//                        err = 1;
//                    }
//                }
//            });
//        }
//    }
    // 메시지 알림 전송
    public void notifyMessage(String userId) {
        System.out.println("notifyMessage 함수 내로 진입");
        if (NotificationController.sseEmitters.containsKey(userId)) {
            System.out.println("userId로 할당된 sseEmitters 존재");
            List<SseEmitter> sseEmitterReceivers = NotificationController.sseEmitters.get(userId);
            for (SseEmitter sseEmitter : sseEmitterReceivers) {
                System.out.println("sseEmitter for문 시작");
                try {
                    sseEmitter.send(SseEmitter.event().name("matching-alarm").data("매칭이 완료되었습니다. 알림함을 확인해주세요"));
                    System.out.println("sseEmitter for문 성공");
                } catch (IOException e) {
                    // 오류 발생 시 연결 종료 및 예외 처리
                    System.out.println("여기 불났어요 불");
                    sseEmitter.completeWithError(e);
                }
            }
        }else{
            System.out.println("userId로 할당된 sseEmitters 가 없음");
        }
    }

    // 매칭 취소 알림
    public void notifyCancel(String userId) throws IOException {
        // 5. 매칭알림을 보낼 대상자 조회
        //User user = userRepository.findByNickname(receiver);

        if (NotificationController.sseEmitters.containsKey(userId)) {
            List<SseEmitter> sseEmitterReceivers = NotificationController.sseEmitters.get(userId);
            sseEmitterReceivers.forEach(sseEmitter -> {
                try {
                    sseEmitter.send(SseEmitter.event().name("matching-cancel").data("매칭이 취소되었습니다. 알림함을 확인해주세요"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }

    }

    //더미데이터 전송을 위한 connect 알림
    public void notifyConnect(String userId) {
        // 5. 매칭취소 알림을 보낼 대상자 조회
        //User user = userRepository.findByNickname(receiver);

        // 6. 대상자 정보로부터 id 값 추출
        //Long userId = user.getId();

        // 7. Map 에서 userId 로 사용자 검색
        if (NotificationController.sseEmitters.containsKey(userId)) {
            List<SseEmitter> sseEmitterReceivers = NotificationController.sseEmitters.get(userId);
            // 8. 알림 메시지 전송 및 해체
            try {
                sseEmitterReceivers.forEach(sseEmitter -> {
                    try {
                        sseEmitter.send(SseEmitter.event().name("connect").data("SseEmitter 연결에 성공했습니다~!"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("여기 불 났어요");
                NotificationController.sseEmitters.remove(userId);
            }
        }
    }
}//Service
