const getClientInfo = async () => {
    try {
        const res = await fetch("/api/mypage/info");
        const data = await res.json();

        console.log("data - ", data)

        //publishing 수정되면 view 로직 수정 예정
        document.getElementById("nickname").innerText = data.nickName;
        document.getElementById("age").innerText = data.age;
        document.getElementById("gender").innerText = getGender(data.gender);
        document.getElementById("level-id").innerText = getLevel(data.levelId);
        document.getElementById("current-exp").innerText = data.currentExp;
        document.getElementById("match-count").innerText = data.matchCount;
        document.getElementById("charge-amount").innerText = data.chargeAmount;
        document.getElementById("point").innerText = data.point;
    } catch (err) {
        console.log(err);
        throw err;
    }
}

//levelId 파싱
const getLevel = (levelId) => {
    switch (levelId) {
        case 1:
            return "BRONZE";
        case 2:
            return "SILVER";
        case 3:
            return "GOLD";
        case 4:
            return "PLATINUM";
        case 5:
            return "DIAMOND";
        default:
            return "UNRANKED";
    }
}

//gender 파싱
const getGender = (gender) => {
    switch (gender) {
        case "M":
            return "남";
        case "F":
            return "여";
        default:
            return 0;
    }
}

//client 프로필 이미지 조회
const getProfileImage = async () => {
    try {
        const res = await fetch("/api/mypage/profile");
        const imageUrl = await res.text();
        document.getElementById("profile-image").src = imageUrl;
    } catch (err) {
        console.log(err);
        throw err;
    }
}

//client 프로필 이미지 업데이트(s3에도 업데이트)
const updateImage = async (file) => {
    try {
        const formData = new FormData();
        formData.append("file", file);
        const res = await fetch("/api/mypage/profile", {
            method: "PUT",
            body: formData
        });
        location.reload(); //이미지 업로드되면 리렌더링해서 프로필사진 최신화
    } catch (err) {
        console.log(err);
        throw err;
    }
}

//파일 선택
const handleFileSelect = (e) => {
    const file = e.target.files[0]; //0번째 인덱스가 해당하는거임
    updateImage(file);
}

document.getElementById("file-input").addEventListener("change", handleFileSelect); //파일변경


document.querySelector(".my-matching-history-list").addEventListener("click", () => {
    window.location.href = "/my-match-history-list";
});

document.querySelector(".cash-charge").addEventListener("click", () => {
    window.location.href = "/mypage/cash-charge";
});




document.querySelector(".my-court-review-list").addEventListener("click", () => {
    window.location.href = "/mypage/my-court-review-list";
});




getProfileImage();
getClientInfo();