// const usersInfoData = {
//     "hteamManagerId": 1157,
//     "ateamManagerId": 6237,
//     "hteamUsersInfo": [
//         {
//             "id": 1157,
//             "levelId": 4,
//             "age": 43,
//             "nickname": "심성호",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 8170,
//             "levelId": 1,
//             "age": 41,
//             "nickname": "손영길",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 3924,
//             "levelId": 4,
//             "age": 46,
//             "nickname": "석촌호수일짱하상현",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 1649,
//             "levelId": 1,
//             "age": 46,
//             "nickname": "한정웅",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 1457,
//             "levelId": 3,
//             "age": 41,
//             "nickname": "오금일짱차우진",
//             "imageUrl": null,
//             "matchCount": 0
//         }
//     ],
//     "ateamUsersInfo": [
//         {
//             "id": 1376,
//             "levelId": 2,
//             "age": 49,
//             "nickname": "황정호",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 6237,
//             "levelId": 3,
//             "age": 49,
//             "nickname": "잠실일짱윤정식",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 9157,
//             "levelId": 4,
//             "age": 45,
//             "nickname": "학동일짱문영철",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 8679,
//             "levelId": 3,
//             "age": 41,
//             "nickname": "문성진",
//             "imageUrl": null,
//             "matchCount": 0
//         },
//         {
//             "id": 4083,
//             "levelId": 5,
//             "age": 46,
//             "nickname": "봉은사일짱나정식",
//             "imageUrl": null,
//             "matchCount": 0
//         }
//     ]
// }
// const courtData = {
//     "courtId": 36,
//     "name": "서울 은평 롯데몰 A구장",
//     "images": [
//         "https://d31wz4d3hgve8q.cloudfront.net/media/Eunpyeong_A_corner.jpg",
//         "https://d31wz4d3hgve8q.cloudfront.net/media/Eunpyeong_A_half.jpg",
//         "https://d31wz4d3hgve8q.cloudfront.net/media/Eunpyeong_A_goal.jpg",
//         "https://d31wz4d3hgve8q.cloudfront.net/media/DSC07845_l4piKiA.jpg",
//         "https://d31wz4d3hgve8q.cloudfront.net/media/Eunpyeong_A_corner2.jpg"
//     ],
//     "scheduleDate": "2024-05-22T00:00:00",
//     "startT": "10",
//     "endT": null,
//     "productPrice": 100000,
//     "lat": "126.9179003",
//     "lng": "37.63753667"
// }

let usersInfoData;
let courtData;
let courtFacilityInfoData;
let courtNoticeData;

let matchHistoryId;
let currentUserId;

const levelImg = {
    1 : '/images/utils/bronze.png',
    2 : '/images/utils/silver.png',
    3 : '/images/utils/gold.png',
    4 : '/images/utils/platinum.png',
    5 : '/images/utils/dia.png',
}
const levelName = {
    1: "BRONZE",
    2: "SILVER",
    3: "GOLD",
    4: "PLATINUM",
    5: "DIAMOND"
}
const nullUserImg = '/images/utils/nullUserImg.png';

const modalData = [
    {
        dialogName: '.info-1-dialog',
        openName: 'button.info-1-Open',
        closeName: 'button.info-1-Close'
    },
    {
        dialogName: '.info-2-dialog',
        openName: 'button.info-2-Open',
        closeName: 'button.info-2-Close'
    },
    {
        dialogName: '.info-3-dialog',
        openName: 'button.info-3-Open',
        closeName: 'button.info-3-Close'
    },
    {
        dialogName: '.info-4-dialog',
        openName: 'button.info-4-Open',
        closeName: 'button.info-4-Close'
    }
]

const getData = async () => {

    // 현재 사용자 user정보
    const userRawInfo = await fetch('/api/auth/kakao/ids');
    const userInfo = await userRawInfo.json();
    currentUserId = userInfo.userId;

    const userRawData = await fetch(`/api/matched/users/detail/${matchHistoryId}`);
    usersInfoData = await userRawData.json();

    const courtRawData = await fetch(`/api/court/${matchHistoryId}`);
    courtData = await courtRawData.json();

    const courtFacilityInfoRawData =await fetch(`/api/stadium/${courtData.courtId}/facilityInfo`);
    courtFacilityInfoData = await courtFacilityInfoRawData.json()

    const courtNoticeRawData = await fetch(`/api/stadium/${courtData.courtId}/notice`);
    courtNoticeData = await courtNoticeRawData.text();
}












const matchInfoHandler = async () => {
    const urlParams = new URLSearchParams(window.location.search);
    matchHistoryId = urlParams.get('matchHistoryId');
    await getData();

    modalData.forEach(item => {
        const dialogName = item.dialogName;
        const openName = item.openName;
        const closeName = item.closeName;

        const infoDialog = document.querySelector(dialogName)
        infoDialog.addEventListener('click', (event) => {
            if (event.target.nodeName === 'DIALOG') {
                infoDialog.close()
            }
        })

        const openInfoModalButton = document.querySelector(openName)
        openInfoModalButton.addEventListener('click', () => {
            infoDialog.showModal()
        })

        const closeInfoModalButton = document.querySelector(closeName)
        closeInfoModalButton.addEventListener('click', () => {
            infoDialog.close()
        })
    });

    const $homeTeamContainer = document.querySelector('.content__main__left .match-team-container:nth-child(1)');
    const $awayTeamContainer = document.querySelector('.content__main__left .match-team-container:nth-child(2)');
    const $contentMainRight = document.querySelector('.content__main__right');

    insertUserInfo($homeTeamContainer, usersInfoData.hteamUsersInfo);
    insertUserInfo($awayTeamContainer, usersInfoData.ateamUsersInfo);
    insertCourtInfo($contentMainRight);
    insertCourtFacility();
    insertCourtNotice();
    insertCourtImg();

    // 이벤트
    addEvents();

    // 캐러셀
    $('.owl-carousel-banner').owlCarousel({
        loop: true,
        margin: 0,
        nav: false,
        // navText: [
        // 	'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(-1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>',
        // 	'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>'
        // ],
        autoplay: true,
        autoplayTimeout: 10000,
        autoplayHoverPause: false,
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 1
            },
            100: {
                items: 1
            }
        }
    })
}

const insertUserInfo = ($container, users) => {
    const $$matchUserInfo = $container.querySelectorAll('.match-user-info');

    $$matchUserInfo.forEach(($matchUserInfo, index) => {
        const $userProfileImg = $matchUserInfo.querySelector('.matchgameinfo__userprofile img');

        $userProfileImg.src = users[index].imageUrl == null ? nullUserImg : users[index].imageUrl;
        $userProfileImg.alt = users[index].nickname;

        const $$moreInfo = $matchUserInfo.querySelectorAll('.more-info .container');

        const $userLevelImage0 = $$moreInfo[0].querySelector('.more-info-value img');
        $userLevelImage0.src = levelImg[users[index].levelId];

        const $moreInfotitle0 = $$moreInfo[0].querySelector('.more-info-title');
        $moreInfotitle0.textContent = levelName[users[index].levelId];

        const $moreInfoValue1 = $$moreInfo[1].querySelector('.more-info-value p');
        $moreInfoValue1.textContent = users[index].matchCount;

        const $moreInfoValue2 = $$moreInfo[2].querySelector('.more-info-value p');
        $moreInfoValue2.textContent = users[index].age;

        const $userLevelImage1 = $matchUserInfo.querySelector('.general .container img');
        $userLevelImage1.src = levelImg[users[index].levelId];

        const $userName = $matchUserInfo.querySelector(".general .container p");
        $userName.textContent = users[index].nickname;

        if (users[index].id === usersInfoData.hteamManagerId || users[index].id === usersInfoData.ateamManagerId) {
            const $manager = $matchUserInfo.querySelector('.general > img');
            $manager.classList.add('manager-checked');
        }
    })
}

const insertCourtInfo = ($contentMainRight) => {
    const $stadiumTitle = $contentMainRight.querySelector('.stadium-title');
    $stadiumTitle.textContent = courtData.name;

    const $contentSchedule = $contentMainRight.querySelector('.content-schedule');
    $contentSchedule.textContent = dateConvertor(courtData.scheduleDate, courtData.startT);

    //장소(수정 필요)
    const $courtAddress = $contentMainRight.querySelector('.content-container > div:nth-child(1)');
    $courtAddress.textContent = courtData.name;

    const $rightCostInfo = $contentMainRight.querySelector('.right-costInfo-container > div:nth-child(2)');
    $rightCostInfo.textContent = (courtData.productPrice / 10).toLocaleString() + " / 1인";
}

const dateConvertor = (dateString, timeString) => {
    const date = new Date(dateString);
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const dayOfWeek = ['일', '월', '화', '수', '목', '금', '토'][date.getDay()];

    const hour = parseInt(timeString, 10);
    const timeRange = `${hour.toString().padStart(2, '0')}:00 ~ ${(hour + 2).toString().padStart(2, '0')}:00`;

    return `${date.getFullYear()}년 ${month}월 ${day}일 (${dayOfWeek}요일) ${timeRange}`;
}

const insertCourtFacility = () => {
    const $$Info2Dialog = document.querySelectorAll('.info-2-dialog .wrapper > div');
    $$Info2Dialog[0].textContent =  courtFacilityInfoData.sizeX + "x" + courtFacilityInfoData.sizeY + "m";
    $$Info2Dialog[1].textContent = courtFacilityInfoData.isParking === 1 ? "주차 가능" : "주차 불가";
    $$Info2Dialog[2].textContent = courtFacilityInfoData.isToilet === 1 ? "화장실 이용 가능" : "화장실 이용 불가";
    $$Info2Dialog[3].textContent = courtFacilityInfoData.isShower === 1 ? "샤워실 이용 가능" : "샤워실 이용 불가";
    $$Info2Dialog[4].textContent = courtFacilityInfoData.isShoes === 1 ? "풋살화 대여 가능" : "풋살화 대여 불가";
}

const insertCourtNotice = () => {
    const $Info3Dialog = document.querySelector('.info-3-dialog .dialog-content-container');
    const parts = courtNoticeData.split('\n').map(part => part.trim());
    parts.forEach((part) => {
        const p = document.createElement('p');
        p.innerText = part;
        $Info3Dialog.appendChild(p);
    })
}

const insertCourtImg = () => {
    const $owlCarousel = document.querySelector('.banner-container .carousel-wrap .owl-carousel')
    if (courtData == null || courtData.images == null || courtData.images.length === 0) {
        createCarouselItem($owlCarousel, "/images/test-banner-img.png")
    } else {
        courtData.images.forEach((img) => {
            createCarouselItem($owlCarousel, img)
        })
    }
    // document.querySelector('.banner-container img').src = courtData.images[0];
}

// banner carousel item 생성
const createCarouselItem = ($owlCarousel, img) => {
    // 생성해야 할 태그
    // <div class="item">
    //     <img src="/images/test-banner-img.png" alt="" class="banner">
    // </div>
    const $item = document.createElement('div');
    $item.className = 'item';

    const $img = document.createElement('img');
    $img.src = img;
    $img.alt = '';
    $img.className = 'banner';

    $item.appendChild($img);

    // $owlCarousel.appendChild($item);
    $owlCarousel.insertBefore($item, $owlCarousel.firstChild);
}

const addEvents = () => {
    //동료 후기 작성 페이지 이동
    const $peerReviewBtn = document.querySelector('.form-container .form__btn-container button:nth-child(1)');
    $peerReviewBtn.addEventListener('click', () => {
        window.location.href = `/peerReview?matchHistoryId=${matchHistoryId}`;
    })

    //구장 후기 작성 페이지 이동
    const $stadiumReviewBtn = document.querySelector('.form-container .form__btn-container button:nth-child(2)');
    $stadiumReviewBtn.addEventListener('click', () => {
        //구장 후기 작성 페이지로 이동
        //만일 해당 인원이 작성한 경우, 수정 페이지로 이동
        //이건 이동 로직 상에 사용자가 작성 했는지 여부를 판별해야 할듯...
        if (true) {
            window.location.href = `/courtReviewRegist?matchHistoryId=${matchHistoryId}&courtId=${courtData.courtId}`;
        } else {
            window.location.href = `/courtReviewUpdate?matchHistoryId=${matchHistoryId}&courtId=${courtData.courtId}`;
        }

    })
}


matchInfoHandler();
