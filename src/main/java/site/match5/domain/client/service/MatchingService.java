//package site.match5.domain.client.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MatchingService {
//    @Autowired
//    private NotificationService notificationService;
//
//    //매칭리스트 데이터 추가되면 completeMatching 함수 실행
//    public void completeMatching(Long matchingId, List<Long> userIds){
//        //0. 매칭 데이터를 db에 추가하는 작업 수행
//        //1.매칭 리스트의 id값을 참조하는 matched_userlist 테이블의 userid를 리스트에 담는다
//
//        //2. 각 사용자에게 알림 전송
//        for(Long userId : userIds){
//            //hashmap에 userid: emitter add 해놨기 때문에, userid로 해당 emitter찾아서 함수 실행
//            //notification이라는 이벤트 생성 + 메세지 데이터 실어 보낸다.
//            notificationService.sendNotification(userId, "매칭이 완료되었습니다. 매칭 결과를 확인하고, 예약을 진행하세요");
//        }
//    }
//
//
//}
