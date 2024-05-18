let userId;
let originalCash; //원래 잔액
let money; //충전할 금액

//현재 세션에 있는 유저ID 받아오기
const getUserInfo = async () => {
    try {
        const res = await fetch("/api/auth/kakao/ids");
        const userInfo = await res.json();
        userId = await userInfo.userId
        await getOriginalCashInfo();
    } catch (err) {
        console.log(err)
        throw err;
    }
}

//기존 잔액 조회
const getOriginalCashInfo = async () => {
    try {
        const res = await fetch(`/api/user/${userId}`)
        const originalCashInfo = await res.json();
        originalCash = originalCashInfo.money;
        console.log(originalCashInfo)
        console.log(originalCashInfo.money)
        currentCashElement.textContent = parseInt(originalCash);
    } catch (err) {
        console.log(err)
        throw err
    }
}

//캐시 충전
document.querySelector('.charge-button').addEventListener('click', async () => {
    try {
        //각 약관 동의 여부 확인
        const termsofuse1 = document.getElementById('termsofuse-1').checked;
        const termsofuse2 = document.getElementById('termsofuse-2').checked;
        const termsofuse3 = document.getElementById('termsofuse-3').checked;

        //하나라도 동의하지 않으면 리턴
        if (!termsofuse1 || !termsofuse2 || !termsofuse3) {
            alert("필수 사항을 모두 체크해주세요.")
            return;
        }

        //PATCH 보낼 데이터
        const data = { money }
        const res = await fetch(`/api/user/${userId}/money`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json;charset=UTF-8"
            },
            body: JSON.stringify(data)
        });
        window.location.href = "/cash-charge";
    } catch (err) {
        console.log(err);
        throw err;
    }
});

//취소
document.querySelector('.not-charge-button').addEventListener('click', async () => {
    window.location.href = "/mypage";
})


//입력칸에 쓰는 내용
const text = document.querySelector('.input-cash');
//입력칸에 입력될떄마다 충전 후 잔액 업데이트
text.addEventListener('input', () => {
    money = text.value;
    console.log(money);
    if (money == "") {
        currentCashElement.textContent = parseInt(originalCash);
    } else {
        currentCashElement.textContent = parseInt(money) + parseInt(originalCash);
    }
});

const currentCashElement = document.querySelector('.current-cash');

getUserInfo();