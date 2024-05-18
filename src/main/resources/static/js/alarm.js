//SSE 관련
$(document).ready(function() {
        console.log('cookie got, sse 연결 전')
        // let userId = 10001;
        let eventSource;
        const connectSSE = () => {
            eventSource = new EventSource(`/subscribe`); //eventsource(서버 엔드포인트)사용해서 서버와 연결
            //해당 주소를 요청하면, mapping된 subscribe 함수가 실행됨
            console.log("connectSSE호출합니다~ eventSource와 연결");


            // 이벤트 소스 연결이 끊겼을 때 처리
            eventSource.onerror = (event) => {
                if (event.currentTarget.readyState === EventSource.CLOSED) {
                    // 연결이 닫혔을 때 다시 연결 시도
                    console.log('Connection closed. Reconnecting...');
                    connectSSE(); // 재연결
                }
            };

            eventSource.onmessage = (event) => {
                console.log('msg: ', event.data)
            }
            //클라이언트는 이벤트 소스를 생성할 때 이벤트의 종류를 등록한다.

            //eventSource에 등록하는 이벤트 1
            //서버로부터 matching-alarm이라는 이벤트가 오면 실행되는 함수
            eventSource.addEventListener("matching-alarm", function(event) {

                //notifyMessage는 marching-alarm이라는 이벤트명으로 데이터를 실어보내는 함수
                //post요청 >> notifyMessage 함수 실행 >> addEventListener(이벤트명, 함수) 의 함수가 실행되는 것
                let message = event.data;
                console.log('matching alarm: ', message);

                //화면에 알림을 직접적으로(모달) 보여주는 함수
                // showMatchingModal(message);

                //빨간 동그라미
                showRedDot();
                
                //알림 모달 목록업데이트
                fetchNotifications();

                //web-notification
                (async () => {
                    // 브라우저 알림 정의
                    const showNotification = () => {
                        const notification = new Notification("MATCH5 알림", {
                            body: message,
                            requireInteraction: true,
                            icon:'images/favicon.png',
                        });

                        // setTimeout(() => {
                        //     notification.close();
                        // }, 10 * 1000);

                        notification.addEventListener('click', () => {
                            // window.open(data.url, '_blank');
                            //window.location.href = 'matchingameinfodetail';
                            notification.close();
                        });

                        // let closeNotification = false;
                        //
                        // //일정 시간이 지난 후에 알림을 닫는 함수
                        // const closeAfterTimeout = () => {
                        //     if(!closeNotification){
                        //         notification.close();
                        //     }
                        // }
                        // // 10초 후에 알림을 닫음 (1000ms * 10)
                        // setTimeout(closeAfterTimeout, 20 * 1000);
                    }

                    // 브라우저 알림 허용 권한
                    let granted = false;

                    if (Notification.permission === 'granted') {
                        granted = true;
                    } else if (Notification.permission !== 'denied') {
                        let permission = await Notification.requestPermission();
                        granted = permission === 'granted';
                    }else{
                    }

                    // 알림 보여주기
                    if (granted) {
                        showNotification();
                    }else{
                    }



                })();

            }); //matching-alarm add eventlistener

            //eventSource에 등록하는 이벤트 2
            eventSource.addEventListener("error", function(event) {
                eventSource.close();
            });
            //eventSource에 등록하는 이벤트 3
            eventSource.addEventListener("matching-cancel", function(event) {
                let message = event.data;
                console.log(message);
                //showMatchingModal(message);
            });
            //eventSource에 등록하는 이벤트 4
            eventSource.addEventListener("connect", function(event) {
                let message = event.data;
                console.log(message);
                //showMatchingModal(message);
            });
        }//connectSSE

    //최초 연결 설정
    connectSSE();
    console.log("connectSSE 실행")


        //사용 X
    // // 알림 실시간으로 화면에 표시하는 함수 정의
    // function showMatchingModal(message) {
    //     // 모달 요소 생성
    //     let modal = document.createElement('div');
    //     modal.classList.add('modal');
    //
    //     // 모달 내용 생성
    //     let modalContent = document.createElement('div');
    //     modalContent.classList.add('modal-content');
    //     modalContent.textContent = message;
    //
    //     // 모달에 내용 추가
    //     modal.appendChild(modalContent);
    //
    //     // body에 모달 추가
    //     document.body.appendChild(modal);
    //
    //     // 모달 닫기 버튼 생성
    //     let closeButton = document.createElement('span');
    //     closeButton.classList.add('close');
    //     closeButton.textContent = '[X]';
    //     modalContent.appendChild(closeButton);
    //
    //     // 닫기 버튼 클릭 이벤트 핸들러
    //     closeButton.onclick = function() {
    //         modal.style.display = 'none';
    //     }
    //
    //     // 모달 표시
    //     modal.style.display = 'block';
    // }

//빨간 동그라미
    function showRedDot() {
        document.getElementById('red-dot').style.display = 'block';
    }
    // function hideRedDot() {
    //     document.getElementById('red-dot').style.display = 'none';
    // }
}); //dom






