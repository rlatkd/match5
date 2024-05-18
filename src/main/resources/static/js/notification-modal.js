// matching_alarm 데이터 사용 & 알림 모달창 토글 관련
// 알림 모달창 이벤트---------------------------------------------
const $notificationContainer = document.getElementById("notification-button");
const $notificationModal = document.getElementById("notification-modal");
const $redDot = document.getElementById("red-dot");
$notificationModal.style.display = "none";
$notificationContainer.addEventListener('click', async () => {
    $redDot.style.display = "none";
    if ($notificationModal.style.display === "none") {
        $notificationModal.style.display = "block";
        await fetchNotifications();

        const $notificationContents = document.querySelectorAll('.notification-content');

        $notificationContents.forEach(($notificationContent) => {
            const alarmType = $notificationContent.dataset.alarmType;
            console.log("alarmType", alarmType);
            if (alarmType === "매칭완료") {
                $notificationContent.addEventListener('click', () => {
                    const matchHistoryId = $notificationContent.dataset.matchHistoryId;
                    console.log("matchingHistoryId : ",matchHistoryId)
                    const newUrl = `/match_info?matchHistoryId=${matchHistoryId}`;
                    console.log(newUrl);
                    window.location.href = newUrl;
                })
            }
        })

    } else {
        $notificationModal.style.display = "none";
    }
})
//
// const $notificationContents = document.querySelectorAll('.notification-content');
// //querySelectorAll 함수를 사용하여 선택하기 전에 요소가 DOM에 완전히 추가되었는지 확인해야 합니다.
// //스크립트가 실행되는 시점에 .notification-content 요소가 아직 생성되지 않았다면, $notificationContents 변수에는 아무런 요소도 포함되지 않을 것
// if ($notificationContents.length === 0) {
//     console.log("NodeList가 비어 있습니다.");
// }
// $notificationContents.forEach(($notificationContent) => {
//     const alarmType = $notificationContent.dataset.alarmType;
//     console.log("alarmType", alarmType);
//     if (alarmType === "매칭") {
//         $notificationContent.addEventListener('click', () => {
//             const matchHistoryId = $notificationContent.dataset.matchHistoryId;
//             console.log("matchingHistoryId : ",matchHistoryId)
//             const newUrl = `/match_info?matchHistoryId=${matchHistoryId}`;
//             window.location.href = newUrl;
//         })
//     }
// })

// 서버로부터 알림 목록을 가져와서 모달 창에 추가
async function fetchNotifications() {

    try {
        console.log("fetchNotifications() try start");
        // data에 fetch
        const response = await fetch(`/api/alarm/user`);
        const data = await response.json();

        // 모달창 전체
        const notificationList = document.getElementById("notification-modal");
        // 기존 목록 비우기
        notificationList.innerHTML = '';

        //개별 알림 <div><p> </p></div> 생성하기
        //알림목록 없을 때
        if(data.length === 0){
            const noNotificationDiv = document.createElement("div");
            noNotificationDiv.classList.add("notification-content");
            const p = document.createElement("p");
            p.textContent = "알림이 존재하지 않습니다.";
            noNotificationDiv.appendChild(p);
            notificationList.appendChild(noNotificationDiv);
            return; // No need to proceed further
        }
        //알림목록 있을 때
        let i = 1;
        data.forEach(notification => {
            console.log(notification);
            const listItem = document.createElement("div");
            listItem.classList.add("notification-content");
            listItem.setAttribute("data-alarm-type", notification.type);
            if(notification.type === "매칭완료"){
                listItem.setAttribute("data-match-history-id", notification.matchingHistoryId);
                console.log(" notification.matching_history_id: ", notification.matchingHistoryId);
                
            }
            const p = document.createElement("p");
            p.textContent =  `${i}  ${notification.content}`; //notification.matching_history_id
            listItem.appendChild(p);
            notificationList.appendChild(listItem);
            i++;
            //notification.matchingHistoryId >> 해당 페이지로 url 연결
        });
    } catch (err) {
        console.error(err);
        throw err;
    }
}

// 날짜 문자열을 원하는 형식으로 변환하는 함수
// function formatDate(dateString) {
//     const date = new Date(dateString);
//     const year = date.getFullYear().toString().slice(-2); // 연도의 마지막 두 자리
//     const month = String(date.getMonth() + 1).padStart(2, '0'); // 월 (0부터 시작하므로 +1)
//     const day = String(date.getDate()).padStart(2, '0'); // 일
//     const hours = String(date.getHours()).padStart(2, '0'); // 시간
//     const minutes = String(date.getMinutes()).padStart(2, '0'); // 분
//
//     return `${year}-${month}-${day} ${hours}:${minutes}`;
// }
