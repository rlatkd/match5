// 페이지 넘버(처음 들어왔을 땐 1페이지)
let pageNum = 1;
let stadiumList;
let userId;  //현재 세션에 있는 유저 ID에는 매칭이력 관련 데이터가 없기때문에
                            // 더미ID 사용 -> 나중엔 바로 아래에있는 api 코드 사용하면됨
                            // 맨 밑에 getUserId() 호출해야됨

//인덱스용 넘버
const itemsPerPage = 5;

const loadPage = async () => {
    await getUserId()
    await getStadiumList();
}

//현재 세션에 있는 유저ID 받아오기
const getUserId = async () => {
    try {
        const res = await fetch("/api/auth/kakao/ids");
        const userIdData = await res.json();
        userId = await parseInt(userIdData.userId);
        //console.log("[userId]", userId)
        // return userId;
    } catch (err) {
        console.log(err)
        throw err;
    }
}

const getStadiumList = async () => {
    try {
        const res = await fetch(`/api/matching/user/${userId}/${pageNum}/${itemsPerPage}`);
        stadiumList = await res.json();
        // console.log("[stadiumList]", stadiumList);
        renderData(stadiumList);
    } catch (err) {
        console.log(err);
        throw err;
    }
}

const renderData = (stadiumList) => {
    let reviewList = $(".review-list-container");

    //목록 초기화
    reviewList.empty();

    //목록 생성
    const startIndex = (pageNum - 1) * itemsPerPage; //시작 인덱스 계산
    for (let i = 0; i < itemsPerPage; i++) {
        let itemIndex = startIndex + i;
        let item = stadiumList[i];
        let itemElement = $("<div>").addClass("item");

        //item 엘리멘트의 데이터셋을 추가

        if (item) { //데이터가 있는 경우
            itemElement.html(`
                <div class="index">${formatDate(item.selectedDate)}</div>
                <div class="stadium_name">${item.courtName}</div>
                <div class="location">${item.status}</div>
            `);

            //아이템 클릭 시 이동 이벤트 추가
            itemElement.click(() => {
                // 클릭된 아이템의 stadiumId를 가져와 URL 구성
                const matchHistoryId = item.id;
                const url = `/match_info_detail?matchHistoryId=${matchHistoryId}`;

                // 페이지 이동
                window.location.href = url;
            });
        } else { //데이터가 없는 경우
            itemElement.html(`
                <div class="index"></div>
                <div class="stadium_name"></div>
                <div class="location"></div>
            `);
        }
        reviewList.append(itemElement);
    }
}

// getStadiumList();

document.addEventListener("DOMContentLoaded", () => {

    //페이지가 로드되었을 때 첫 번째 페이지 버튼에 클릭된 색상 적용
    document.querySelector('.page-buttons button:first-child').style.backgroundColor = 'green';
    document.querySelector('.page-buttons button:first-child').style.color = 'white';

    //페이지 버튼 클릭 시 해당 페이지의 목록 표시
    document.querySelector('.page-buttons').addEventListener('click', (e) => {
        if (e.target.tagName === 'BUTTON') {
            pageNum = parseInt(e.target.innerText);
            getStadiumList();

            //모든 버튼의 배경색 초기화
            document.querySelectorAll('.page-buttons button').forEach(button => {
                button.style.backgroundColor = '';
                button.style.color = '';
            });

            //클릭된 버튼의 배경색을 녹색으로 변경
            e.target.style.backgroundColor = 'green';
            e.target.style.color = 'white';
        }
    });
});

const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear() - 2000; // 2024 -> 24년
    const month = date.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더함
    const day = date.getDate();
    let hour = date.getHours();
    const ampm = hour < 12 ? 'am' : 'pm';
    if (hour > 12) {
        hour -= 12;
    }
    const minutes = date.getMinutes();

    // 월, 일, 시간 등이 한 자리 수인 경우 앞에 0을 붙여 두 자리로 만듦
    const formattedMonth = month < 10 ? '0' + month : month;
    const formattedDay = day < 10 ? '0' + day : day;
    const formattedHour = hour < 10 ? '0' + hour : hour;
    const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;

    return `${year}년 ${formattedMonth}월 ${formattedDay}일 ${formattedHour}:${formattedMinutes} ${ampm}`;
}

loadPage();