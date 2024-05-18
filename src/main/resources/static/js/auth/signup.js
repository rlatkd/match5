// 클릭한 데이터를 담을 객체
let clientData = {
    kakaoId: null,
    nickName: null,
    gender: null,
    age: null,
    levelId: null,
    currentExp: null
};

//로컬스토리지에 있는 카카오id 담기
setTimeout(() => {
    const kakaoId = JSON.parse(localStorage.getItem("kakaoId")).kakaoId;
    clientData = {
        ...clientData,
        kakaoId: kakaoId
    };
}, 1000);

// 버튼 클릭 이벤트
document.getElementById("level1").addEventListener("click", () => handleButtonClick(1));
document.getElementById("level2").addEventListener("click", () => handleButtonClick(2));
document.getElementById("level3").addEventListener("click", () => handleButtonClick(3));
document.getElementById("level4").addEventListener("click", () => handleButtonClick(4));
document.getElementById("level5").addEventListener("click", () => handleButtonClick(5));

// 버튼 클릭 시 데이터를 담는 함수
const handleButtonClick = (levelId) => {
    clientData = {
        ...clientData,
        levelId: levelId,
        currentExp: parseInt(levelId) * 200 - 100
    };
};

// 회원가입 버튼 클릭 시 데이터 쏨
document.getElementById("signup-button").addEventListener("click", async () => {
    try {

        document.querySelector('.content-div-nickName').style.border = '';
        document.querySelector('.content-div-gender').style.border = '';
        document.querySelector('.content-div-age').style.border = '';
        document.querySelector('.level-div').style.border = '';

        let gender = document.getElementById("gender").value;
        // gender 값을 변환
        if (gender === "male") {
            gender = "M";
        } else if (gender === "female") {
            gender = "F";
        }

        clientData = {
            ...clientData,
            nickName: document.getElementById("nickName").value,
            gender: gender,
            age: document.getElementById("age").value
        };

        if (clientData.nickName === "") {
            document.querySelector('.content-div-nickName').style.border = '2px solid red';
            alert("모든 조건을 입력하세요.")
            return;

        } else if (clientData.gender === "") {
            document.querySelector('.content-div-gender').style.border = '2px solid red';
            alert("모든 조건을 입력하세요.")
            return;

        } else if (clientData.age === "") {
            document.querySelector('.content-div-age').style.border = '2px solid red';
            alert("모든 조건을 입력하세요.")
            return;

        } else if (clientData.levelId === null) {
            document.querySelector('.level-div').style.border = '2px solid red';
            alert("모든 조건을 입력하세요.")
            return;

        } else {
            const res = await fetch("/api/auth/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                },
                body: JSON.stringify(clientData)
            });

            const msg = await res.text();
            alert("회원가입이 성공적으로 완료되었습니다. 다시 로그인을 시도해주세요.");
            setTimeout(() => window.location.href = "/login", 500)}
    } catch (err) {
        console.log(err);
        throw err;
    }
});

// 각 레벨 버튼 요소를 가져옵니다.
const levelButtons = document.querySelectorAll('.level-div button');

// 각 버튼에 클릭 이벤트를 추가합니다.
levelButtons.forEach(button => {
    button.addEventListener('click', function() {
        // 모든 버튼의 색상을 초기화합니다.
        levelButtons.forEach(btn => {
            btn.parentNode.classList.remove('selected');
        });

        // 현재 클릭된 버튼의 부모 div에 'selected' 클래스를 추가하여 색상을 변경합니다.
        this.parentNode.classList.add('selected');
    });
});