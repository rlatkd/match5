const queryParams = new URLSearchParams(window.location.search);
const code = queryParams.get("code");

//kakao 토큰 받아오기
const getAccessToken = async (code) => {
    try {
        const res = await fetch("/api/auth/kakao/token", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ code }),
        });
        const token = await res.json();
        return token;
    } catch (err) {
        console.log(err);
        throw err;
    }
};

//카카오액세스 성공하면 카카오유저 정보 가져오기
const getKakaoUserInfo = async (token) => {
    try {
        const res = await fetch("/api/auth/kakao/info", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(token),
        });
        const kakaoId = await res.json();
        const authenticatedId = await checkAuthentication(kakaoId);
        if (authenticatedId == kakaoId.kakaoId) {
            window.location.href = "/"; //이미 존재하는 회원이면 로그인 및 홈으로 이동
        }
        localStorage.setItem("kakaoId", JSON.stringify(kakaoId)); //localStorage에 kakaoID 저장
        return kakaoId;
    } catch (err) {
        console.log(err);
        throw err;
    }
};

//DB에 해당 계정 정보 존재하는지 확인
const checkAuthentication = async (kakaoId) => {
    try {
        const res = await fetch("/api/auth/kakao/check", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(kakaoId),
        });
        const isClientExist = await res.text();
        console.log(isClientExist);
        console.log(typeof isClientExist);
        return isClientExist;
    } catch (err) {
        console.log(err);
        throw err;
    }
};

//카카오 연결 끝
const endAccess = async (token) => {
    try {
        const res = await fetch("/api/auth/kakao/close", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(token),
        });
        const msg = await res.text();
    } catch (err) {
        console.log(err);
        throw err;
    }
};

//비동기 처리
const getAuthentication = async () => {
    try {
        const token = await getAccessToken(code);
        const kakaoId = await getKakaoUserInfo(token);
        await endAccess(token);
        setTimeout(() => {
            window.location.href = "/signup";
        }, 1000);
    } catch (err) {
        console.log(err);
        throw err;
    }
};

getAuthentication();
