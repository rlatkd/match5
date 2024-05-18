// let currentURL = window.location.href;
// let queryString = currentURL.split('?')[1];
// console.log("[queryString]", queryString)
// let courtId = queryString;

let courtId = 36; //이전 페이지(localhost:8080/api/matching/court/515)에서 넘어온 uri의 queryString
let userId;
let content;
let reviewRate = 0; //아무 별도 클릭안하면 0으로 초기화

//[GET] - localhost:8080/api/stadium/court/36
//name, images 해야됨

//구장 정보
let courtInfo;

//구장 정보 조회
//이건 화면에 보이는 구장이름, 사진용...
const getCourtInfo = async () => {
    try {
        const res = await fetch(`/api/stadium/court/${courtId}`);
        courtInfo = await res.json();
        //console.log("[courtInfo]", courtInfo)

        document.querySelector('.left-container .title span').textContent = courtInfo.name;
        document.querySelector('.banner').setAttribute('src', courtInfo.images[0]);

        return courtInfo;
    } catch (err) {
        console.log(err);
        throw err;
    }
}

//[GET] - localhost:8080/api/auth/kakao/ids
//현재 내 userId 해야됨

//현재 세션에 있는 유저ID 받아오기
const getUserId = async () => {
    try {
        const res = await fetch("/api/auth/kakao/ids");
        const userIdData = await res.json();
        userId = userIdData.userId
        //console.log("[userId]", userId)
        return userId;
    } catch (err) {
        console.log(err)
        throw err;
    }
}

const setReviewRate = (rating) => {
    reviewRate = rating;
}

// 라벨 클릭 이벤트 핸들러
document.querySelectorAll('.rating label').forEach(label => {
    label.addEventListener('click', function() {
        let rating = parseInt(this.getAttribute('for').split('-')[1]);
        setReviewRate(rating);
    });
});

//입력칸에 쓰는 내용
const text = document.querySelector('.write-review-container textarea');
//입력칸에 입력될떄마다 courtReview.content 업데이트
text.addEventListener('input', () => {
    content = text.value;
    //console.log("[content]", content)
});

//[POST] - localhost:8080/api/review/court
// {
//     "userId" : 11,
//     "courtId" : 22,
//     "content" : "구장이 좀 더러워요233",
//     "reviewRate" : 3
// }
//등록 버튼 클릭 시 데이터 쏨
document.querySelector('.startform__submit-container .review-regist').addEventListener('click', async () => {
    try {

        //POST로 보낼 데이터
        const courtReview = {
            userId,
            courtId,
            content,
            reviewRate
        }
        //console.log("[courtReview]", courtReview);

        const res = await fetch("/api/review/court", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8"
            },
            body: JSON.stringify(courtReview)
        });

        const msg = await res.text();
        //console.log("[msg]", msg);
        return msg;
    } catch (err) {
        console.log(err);
        throw err;
    }
});

getCourtInfo();
getUserId();

