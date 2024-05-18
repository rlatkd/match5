//API에서 데이터를 가져온다고 가정
const fetchingLeft = () => {
    return new Promise((resolve, reject) => {
        //가정-1초뒤에 데이터가 왔음
        setTimeout(() =>
            resolve(
        {
            "hteamManagerId": 4,
            "ateamManagerId": 10,
            "hteamUsersInfo": [
                {
                    "id": 3,
                    "levelId": 2,
                    "age": 25,
                    "nickname": "김111",
                    "imageUrl": "/images/userprofile/user1.jpg",
                    "matchCount": 1
                },
                {
                    "id": 4,
                    "levelId": 2,
                    "age": 48,
                    "nickname": "김222",
                    "imageUrl": "/images/userprofile/user2.jpg",
                    "matchCount": 2
                },
                {
                    "id": 1,
                    "levelId": 3,
                    "age": 48,
                    "nickname": "김333",
                    "imageUrl": "/images/userprofile/user3.jpg",
                    "matchCount": 3
                },
                {
                    "id": 10,
                    "levelId": 4,
                    "age": 39,
                    "nickname": "김444",
                    "imageUrl": "/images/userprofile/user4.jpg",
                    "matchCount": 4
                },
                {
                    "id": 2,
                    "levelId": 4,
                    "age": 25,
                    "nickname": "김555",
                    "imageUrl": "/images/userprofile/user5.jpg",
                    "matchCount": 5
                }
            ],
            "ateamUsersInfo": [
                {
                    "id": 2,
                    "levelId": 4,
                    "age": 25,
                    "nickname": "이111",
                    "imageUrl": "/images/userprofile/user1.jpg",
                    "matchCount": 1
                },
                {
                    "id": 4,
                    "levelId": 2,
                    "age": 29,
                    "nickname": "이222",
                    "imageUrl": "/images/userprofile/user2.jpg",
                    "matchCount": 2
                },
                {
                    "id": 10,
                    "levelId": 3,
                    "age": 20,
                    "nickname": "이333",
                    "imageUrl": "/images/userprofile/user3.jpg",
                    "matchCount": 3
                },
                {
                    "id": 7,
                    "levelId": 1,
                    "age": 46,
                    "nickname": "이444",
                    "imageUrl": "/images/userprofile/user4.jpg",
                    "matchCount": 4
                },
                {
                    "id": 6,
                    "levelId": 10,
                    "age": 34,
                    "nickname": "이555",
                    "imageUrl": "/images/userprofile/user5.jpg",
                    "matchCount": 5
                }
            ]
        })
        , 1000);
    });
}

const getLeftData = async () => {
    try {
        const leftData = await fetchingLeft();

        // Home 팀 정보 채우기
        const homeTeamContainer = document.querySelector(".match-team-container#home-team-container");
        fillTeamInfo(homeTeamContainer, leftData.hteamUsersInfo, leftData.hteamManagerId);

        // Away 팀 정보 채우기
        const awayTeamContainer = document.querySelector(".match-team-container#away-team-container");
        fillTeamInfo(awayTeamContainer, leftData.ateamUsersInfo, leftData.ateamManagerId);
    } catch (err) {
        console.log(err);
        throw err;
    }
}

//레벨id에 해당하는 image 경로 파싱
const getLevelImage = (levelId) => {
    switch (levelId) {
        case 1:
            return "/images/utils/bronze.png"; // 경로를 수정하세요
        case 2:
            return "/images/utils/silver.png"; // 경로를 수정하세요
        case 3:
            return "/images/utils/gold.png"; // 경로를 수정하세요
        case 4:
            return "/images/utils/platinum.png"; // 경로를 수정하세요
        case 5:
            return "/images/utils/diamond.png"; // 경로를 수정하세요
        default:
            return "";
    }
}

//레벨id에 해당하는 이름 파싱
const getLevelName = (levelId) => {
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
            return "";
    }
}




const createUserCard = (userInfo, isManager) => {
    // DocumentFragment 생성
    const fragment = document.createDocumentFragment();

    // match-userinfo-card 요소 생성
    const userCard = document.createElement("div");
    userCard.classList.add("match-userinfo-card");

    // additional 요소 생성
    const additionalDiv = document.createElement("div");
    additionalDiv.classList.add("additional");

    // user-card 요소 생성
    const userCardDiv = document.createElement("div");
    userCardDiv.classList.add("user-card");

    // matchgameinfo__userprofile 요소 생성
    const userProfileDiv = document.createElement("div");
    userProfileDiv.classList.add("matchgameinfo__userprofile");

    // 이미지 요소 생성
    const img = document.createElement("img");
    img.src = userInfo.imageUrl;
    img.alt = "";

    // 이미지를 matchgameinfo__userprofile에 추가
    userProfileDiv.appendChild(img);
    userCardDiv.appendChild(userProfileDiv);
    additionalDiv.appendChild(userCardDiv);

    // more-info 요소 생성
    const moreInfoDiv = document.createElement("div");
    moreInfoDiv.classList.add("more-info");

    // 각 정보에 대한 container 요소 생성 및 추가
    const levelContainer = createContainer("more-info-value", getLevelImage(userInfo.levelId), getLevelName(userInfo.levelId));
    const matchCountContainer = createContainer("more-info-value", "", userInfo.matchCount, "경기수");
    const ageContainer = createContainer("more-info-value", "", userInfo.age, "나이");

    moreInfoDiv.appendChild(levelContainer);
    moreInfoDiv.appendChild(matchCountContainer);
    moreInfoDiv.appendChild(ageContainer);
    additionalDiv.appendChild(moreInfoDiv);

    // general 요소 생성
    const generalDiv = document.createElement("div");
    generalDiv.classList.add("general");

    // container 요소 생성
    const containerDiv = createContainer("container", "", userInfo.nickname);

    // 매니저 이미지 추가
    const managerImg = document.createElement("img");
    managerImg.src = "/images/utils/manager.png";
    managerImg.alt = "";
    managerImg.classList.add("manager");
    if (isManager) {
        managerImg.classList.add("manager-checked");
    }
    generalDiv.appendChild(managerImg);

    generalDiv.appendChild(containerDiv);

    // match-userinfo-card에 additional과 general을 추가
    userCard.appendChild(additionalDiv);
    userCard.appendChild(generalDiv);

    // fragment에 match-userinfo-card 추가
    fragment.appendChild(userCard);

    return fragment;
}


const createContainer = (className, imagePath, textContent, additionalText) => {
    const container = document.createElement("div");
    container.classList.add(className);

    if (imagePath) {
        const img = document.createElement("img");
        img.src = imagePath;
        img.alt = "";
        container.appendChild(img);
    }

    const span = document.createElement("span");
    span.textContent = textContent;
    container.appendChild(span);

    if (additionalText) {
        const additionalSpan = document.createElement("span");
        additionalSpan.textContent = additionalText;
        container.appendChild(additionalSpan);
    }

    return container;
}

const fillTeamInfo = (container, usersInfo, managerId) => {
    usersInfo.forEach(userInfo => {
        const isManager = userInfo.id === managerId;
        const card = createUserCard(userInfo, isManager);
        container.appendChild(card);
    });
}




document.addEventListener("DOMContentLoaded", function() {
    getLeftData();
});



















//API에서 데이터를 가져온다고 가정
const fetchingRight = () => {
    return new Promise((resolve, reject) => {
        //가정-1초뒤에 데이터가 왔음
        setTimeout(() =>
            resolve({
                "courtId": 24,
                "name": "노원하라A",
                "images": [
                "https://d31wz4d3hgve8q.cloudfront.net/media/coner_U7jEVnu.jpg",
                "https://d31wz4d3hgve8q.cloudfront.net/media/goalline_55nKjvh.jpg",
                "https://d31wz4d3hgve8q.cloudfront.net/media/halfline_JuMQ19j.jpg"
            ],
                "scheduleDate": "2024-05-24T00:00:00",
                "startT": "16",
                "endT": "18",
                "productPrice": 11000,

                //좌표값으로 location 가져오는거 필요함
                "location": "서울 용산구 한강대로23길 55 (한강로3가) HDC아이파크몰 리빙파크 8F / 9F / 10F",


                "lat": "127.0626195",
                "lng": "37.64680948"
            }), 1000);
    });
}

//stadiumTitle: "용산 아이파크몰 (7구장)",
//schedule: "2024년 05월 04일 (토요일) 14:00 ~ 16:00",
//location: "서울 용산구 한강대로23길 55 (한강로3가) HDC아이파크몰 리빙파크 8F / 9F / 10F",
//cost: "11,000원 / 1인"

const getRightData = async () => {
    try {
        const rightData = await fetchingRight(); //원래는 res = await fetch({API_URL}) / rightData = res.json();
        console.log("rightData - ", rightData)

        //시작시간 및 종료시간 형식 변환
        const startDate = new Date(rightData.scheduleDate);
        const startTime = rightData.startT ? rightData.startT.padStart(2, "0") + ":00" : "";
        const endTime = rightData.endT ? rightData.endT.padStart(2, "0") + ":00" : "";

        //날짜 및 시간 형식 변환
        const formattedDate = `${startDate.getFullYear()}-${(startDate.getMonth() + 1).toString().padStart(2, "0")}-${startDate.getDate().toString().padStart(2, "0")}`;
        const formattedTime = `${startTime} ~ ${endTime}`;

        //가격 형식으로 변환
        const formattedPrice = rightData.productPrice.toLocaleString("ko-KR") + " 원 / 1인";

        document.getElementById("stadium-title-placeholder").innerText = rightData.name;
        document.getElementById("content-schedule-placeholder").innerText = `${formattedDate} (${getDayOfWeek(startDate)}) ${formattedTime}`;
        document.getElementById("location-placeholder").innerText = rightData.location;
        document.getElementById("cost-info-placeholder").innerText = `${formattedPrice}`
    } catch (err) {
        console.log(err);
        throw err;
    }
}

//요일 가져옴
const getDayOfWeek = (date) => {
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
    return daysOfWeek[date.getDay()];
}


getRightData();