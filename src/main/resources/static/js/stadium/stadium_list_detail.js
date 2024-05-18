let stadiumId;
let stadiumInfoData;
let stadiumFacilityInfoData;  //구장 특이사항
let stadiumNoticeData;    //구장 정보

const modalData = [
    {
        dialogName: '.info-2-dialog',
        openName: 'button.info-2-Open',
        closeName: 'button.info-2-Close'
    },
    {
        dialogName: '.info-3-dialog',
        openName: 'button.info-3-Open',
        closeName: 'button.info-3-Close'
    }
]

// stadiumInfoData = {
//     "name": "서울 노원 RC풋살스타디움",
//     "lat": 127.074776,
//     "lng": 37.639194,
//     "images": [
//         "https://d31wz4d3hgve8q.cloudfront.net/media/IMG_1953.jpeg",
//         "https://d31wz4d3hgve8q.cloudfront.net/media/IMG_1957.jpeg",
//         "https://d31wz4d3hgve8q.cloudfront.net/media/IMG_1982.jpeg",
//         "https://d31wz4d3hgve8q.cloudfront.net/media/IMG_1980.jpeg"
//     ],
//     "stadiumAvgRate": 3.5,
//     "reviewCount": 6,
//     "reviewList": [
//         {
//             "id": 200,
//             "userId": 11,
//             "courtId": 22,
//             "content": "구장이 좀 더러워요233",
//             "reviewRate": 3,
//             "createdAt": "2024-05-15 05:20:03"
//         },
//         {
//             "id": 201,
//             "userId": 11,
//             "courtId": 22,
//             "content": "구장이 좀 더러워요233",
//             "reviewRate": 3,
//             "createdAt": "2024-05-15 05:26:35"
//         },
//         {
//             "id": 203,
//             "userId": 11,
//             "courtId": 22,
//             "content": "구장이 좀 더러워요!!!",
//             "reviewRate": 3,
//             "createdAt": "2024-05-15 07:27:32"
//         },
//         {
//             "id": 223,
//             "userId": 10005,
//             "courtId": 22,
//             "content": "여긴 정말 최고입니다.!!!!!!아주 좋아요",
//             "reviewRate": 4,
//             "createdAt": "2024-05-16 03:20:19"
//         },
//         {
//             "id": 206,
//             "userId": 11,
//             "courtId": 22,
//             "content": "구장이 좀 더러워요233",
//             "reviewRate": 3,
//             "createdAt": "2024-05-15 07:46:39"
//         },
//         {
//             "id": 222,
//             "userId": 10005,
//             "courtId": 22,
//             "content": "여기 굉장이 아늑하고 따뜻해요!",
//             "reviewRate": 5,
//             "createdAt": "2024-05-16 03:16:25"
//         }
//     ]
// }
// stadiumFacilityInfoData = {
//     "isParking": 1,
//     "isToilet": 0,
//     "isShower": 0,
//     "isShoes": 0,
//     "sizeX": 30,
//     "sizeY": 15
// }
// stadiumNoticeData = "- 샤워실 이용 금지 / 공, 조끼 대여 불가 (코로나 확산 방지 차원 / 별도의 안내 시 까지)\n" +
//     "- 풋살화 2시간 6,000원 유료 대여(우천시 대여 불가)\n" +
//     "- 22:30 이전 현금,카드,계좌이체 // 22:30 이후 현금,계좌이체만 가능\n" +
//     "- 기상악화(눈, 비, 한파 등)로 인하여 미끄러울 수 있으며, 넘어짐 부상으로 인한 도움은 어려울 수 있습니다."

// localhost:8080/api/stadium/36/facilityInfo/byStaduim
// localhost:8080/api/stadium/풋살장아이디/facilityInfo/byStaduim
// localhost:8080/api/stadium/36/notice/byStaduim
// localhost:8080/api/stadium/풋살장아이디/notice/byStaduim [반환 text임]

// localhost:8080/api/stadium/풋살장번호(stadiumId)/infoDetail

const loadPage = async () => {
    const urlParams = await new URLSearchParams((window.location.search));
    stadiumId = await urlParams.get('stadiumId');
    console.log(stadiumId)

    await getData();    //비동기

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

    await insertData();
    await insertReviewContainer();
    await insertCourtFacility();
    await insertCourtNotice();
    await insertCourtImg();

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

const getData = async () => {
    console.log(stadiumId);
    //현재 구장 정보
    const stadiumInfoRawData = await fetch(`/api/stadium/${stadiumId}/infoDetail`);
    stadiumInfoData = await stadiumInfoRawData.json();

    const stadiumFacilityInfoRawData = await fetch(`/api/stadium/${stadiumId}/facilityInfo`);
    stadiumFacilityInfoData = await stadiumFacilityInfoRawData.json();
    // console.log(stadiumFacilityInfoData)

    const stadiumNoticeRawData = await fetch(`/api/stadium/${stadiumId}/notice`)
    stadiumNoticeData = await stadiumNoticeRawData.text()
}

const insertData = () => {
    const $$contentInfoContainer = document.querySelectorAll('.content__main .content__info__container');

    // 구장
    $$contentInfoContainer[0].querySelector('.left-container .title span').textContent = stadiumInfoData.name;

    //주소(지도연결 필요)
    $$contentInfoContainer[0].querySelector('.left-container .content-address-container > p:nth-child(1)').textContent = stadiumInfoData.name;

    //평점
    $$contentInfoContainer[1].querySelector('.content__left-container .rating-score').innerHTML = `${stadiumInfoData.stadiumAvgRate} / <span>5</span>`;

    //별점
    $$contentInfoContainer[1].querySelector('.content__left-container .rating-container .star-rating-score').style.width = countToScore(stadiumInfoData.stadiumAvgRate);

    //참여 인원
    $$contentInfoContainer[1].querySelector('.content__left-container .rating-count').textContent = stadiumInfoData.reviewCount + "명 참여";
}

const insertReviewContainer = () => {
    const $contentRightContainer = document.querySelector('.content__info__container .content__right-container');

    if (stadiumInfoData === null || stadiumInfoData.reviewList === null || stadiumInfoData.reviewList.length === 0) return;

    stadiumInfoData.reviewList.forEach((review) => {
        let $userReviewContainer = document.createElement('div');
        $userReviewContainer.className = 'user-review-container';

        let $userReviewScoreContainer = document.createElement('div');
        $userReviewScoreContainer.className = 'user-review-score-container';

        let $starRating = document.createElement('div');
        $starRating.classList.add('star-rating', 'review');

        let $starRatingScore = document.createElement('span');
        $starRatingScore.className = 'star-rating-score';
        $starRatingScore.style.width = countToScore(review.reviewRate);

        $starRating.appendChild($starRatingScore);

        let $reviewRatingScore = document.createElement('p');
        $reviewRatingScore.className = 'review-rating-score';
        $reviewRatingScore.innerHTML = `&nbsp;&nbsp;${review.reviewRate}`

        $userReviewScoreContainer.appendChild($starRating);
        $userReviewScoreContainer.appendChild($reviewRatingScore)

        let $userReviewContent = document.createElement('div');
        $userReviewContent.className = 'user-review-content';
        $userReviewContent.textContent = review.content;

        let $userReviewFooter = document.createElement('div');
        $userReviewFooter.className = 'user-review-footer';
        $userReviewFooter.innerHTML = `<span>${maskedString(review.nickname)}</span>&nbsp;&middot;&nbsp;<span>${review.createdAt}</span>`

        $userReviewContainer.appendChild($userReviewScoreContainer);
        $userReviewContainer.appendChild($userReviewContent);
        $userReviewContainer.appendChild($userReviewFooter);

        // $contentRightContainer.appendChild($userReviewContainer);
        $contentRightContainer.insertBefore($userReviewContainer, $contentRightContainer.firstChild);
    })
}

const insertCourtFacility = () => {
    const $$Info2Dialog = document.querySelectorAll('.info-2-dialog .wrapper > div');
    $$Info2Dialog[0].textContent =  stadiumFacilityInfoData.sizeX + "x" + stadiumFacilityInfoData.sizeY + "m";
    $$Info2Dialog[1].textContent = stadiumFacilityInfoData.isParking === 1 ? "주차 가능" : "주차 불가";
    $$Info2Dialog[2].textContent = stadiumFacilityInfoData.isToilet === 1 ? "화장실 이용 가능" : "화장실 이용 불가";
    $$Info2Dialog[3].textContent = stadiumFacilityInfoData.isShower === 1 ? "샤워실 이용 가능" : "샤워실 이용 불가";
    $$Info2Dialog[4].textContent = stadiumFacilityInfoData.isShoes === 1 ? "풋살화 대여 가능" : "풋살화 대여 불가";
}

const insertCourtNotice = () => {
    const $Info3Dialog = document.querySelector('.info-3-dialog .dialog-content-container');
    const parts = stadiumNoticeData.split('\n').map(part => part.trim());
    parts.forEach((part) => {
        const p = document.createElement('p');
        p.innerText = part;
        $Info3Dialog.appendChild(p);
    })
}


const insertCourtImg = () => {
    const $owlCarousel = document.querySelector('.banner-container .carousel-wrap .owl-carousel')
    if (stadiumInfoData == null || stadiumInfoData.images == null || stadiumInfoData.images.length === 0) {
        createCarouselItem($owlCarousel, "/images/test-banner-img.png")
    } else {
        stadiumInfoData.images.forEach((img) => {
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

const countToScore = (num) => {
    return ((parseFloat(num) / 5) * 100) + "%";
}

const maskedString = (a) => {
    console.log(a)
    let firstFourChars;
    let maskedString;
    if (a.length > 4) {
        firstFourChars = a.substring(0, 4);
        maskedString = firstFourChars + "*".repeat(a.length - 4);
    } else {
        firstFourChars = a.substring(0, 2);
        maskedString = firstFourChars + "*".repeat(a.length - 2);
    }
    return maskedString;
}

loadPage();