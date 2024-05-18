let url //카카오 로그인 진입 URL

const getKakaoUrl = async () => {
    try {
        const res = await fetch("/api/auth/login");
        const data = await res.json();
        url = data.url;
        return url;
    } catch (err) {
        console.log(err);
        throw err;
    }
}

const handleLoginButtonClick = async () => {
    try {
        const url = await getKakaoUrl();
        window.location.href = url;
    } catch (err) {
        console.error(err);
        throw err;
    }
};

document.getElementById("kakao-button").addEventListener("click", handleLoginButtonClick);

getKakaoUrl();