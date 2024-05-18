// let currentURL = window.location.href;
// let queryString = currentURL.split('?')[1];
// console.log("[queryString]", queryString)
// let courtId = queryString;

let courtId; //이전 페이지(localhost:8080/api/matching/court/515)에서 넘어온 uri의 queryString
let userId;
let content;
let reviewRate = 0; //아무 별도 클릭안하면 0으로 초기화

let courtInfo;  //구장 정보

// 모달------------------------------------------------
const modalData = [
    {
        dialogName: '.info-success-dialog',
        openName: 'button.info-success-Open',
        closeName: 'button.info-success-Close'
    },
    {
        dialogName: '.info-error-dialog',
        openName: 'button.info-error-Open',
        closeName: 'button.info-error-Close'
    }
]

const modalHandler = () => {
    modalData.forEach(item => {
        const dialogName = item.dialogName;
        // const openName = item.openName;
        const closeName = item.closeName;

        const infoDialog = document.querySelector(dialogName)
        infoDialog.addEventListener('click', (event) => {
            if (event.target.nodeName === 'DIALOG') {
                infoDialog.close()
            }
        })

        // const openInfoModalButton = document.querySelector(openName)
        // if (openInfoModalButton != null) {
        //     openInfoModalButton.addEventListener('click', () => {
        //         infoDialog.showModal()
        //     })
        // }

        const closeInfoModalButton = document.querySelector(closeName)
        if (closeInfoModalButton != null) {
            if (dialogName === '.info-error-dialog') {
                closeInfoModalButton.addEventListener('click', () => {
                    infoDialog.close()
                })
            } else {
                closeInfoModalButton.addEventListener('click', () => {
                    infoDialog.close()
                    window.location.href = `/match_info_detail?matchHistoryId=${matchHistoryId}`
                })
            }
        }
    });
}

modalHandler();
// 모달 끝------------------------------------------------

const loadPage = async () => {
    const urlParams = new URLSearchParams(window.location.search);
    matchHistoryId = urlParams.get('matchHistoryId');
    courtId = urlParams.get('courtId');

    await getData();
    console.log()
    insertCourtInfo();
    insertCourtImg();

    // 이벤트
    addEvent();

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
    await getCourtInfo();
    await getUserId();
}

//구장 정보 조회
const getCourtInfo = async () => {
    try {
        const res = await fetch(`/api/stadium/court/${courtId}`);
        courtInfo = await res.json();
    } catch (err) {
        console.log(err);
        throw err;
    }
}

//현재 세션에 있는 유저ID 받아오기
const getUserId = async () => {
    try {
        const res = await fetch("/api/auth/kakao/ids");
        const userIdData = await res.json();
        userId = await userIdData.userId
    } catch (err) {
        console.log(err)
        throw err;
    }
}

const insertCourtInfo = () => {
    document.querySelector('.left-container .title span').textContent = courtInfo.name;
    // document.querySelector('.banner').setAttribute('src', courtInfo.images[0]);
}

const insertCourtImg = () => {
    const $owlCarousel = document.querySelector('.banner-container .carousel-wrap .owl-carousel')
    if (courtInfo == null || courtInfo.images == null || courtInfo.images.length === 0) {
        createCarouselItem($owlCarousel, "/images/test-banner-img.png")
    } else {
        courtInfo.images.forEach((img) => {
            createCarouselItem($owlCarousel, img)
        })
    }
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

const addEvent = () => {
    // 라벨 클릭 이벤트 핸들러
    document.querySelectorAll('.rating label').forEach(label => {
        label.addEventListener('click', function () {
            reviewRate = parseInt(this.getAttribute('for').split('-')[1]);
        });
    });

    const $writeReviewContainerTextarea = document.querySelector('.write-review-container textarea');
    //입력칸에 입력될떄마다 courtReview.content 업데이트
    $writeReviewContainerTextarea.addEventListener('input', () => {
        content = $writeReviewContainerTextarea.value;
    });

    //등록 버튼 클릭 시 데이터 쏨
    document.querySelector('.startform__submit-container .review-regist').addEventListener('click', async (e) => {
        e.preventDefault();
        if (reviewRate !== 0 && content !== undefined && content.trim() !== "" && content.trim().length >=10) {
            document.querySelector(modalData[0].dialogName).showModal();
            await postData();
        } else {
            document.querySelector(modalData[1].dialogName).showModal();
        }
    });
}

const postData = async () => {
    try {
        //POST로 보낼 데이터
        const courtReview = {
            userId,
            courtId,
            content,
            reviewRate
        }

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
}


loadPage();

//[POST] - localhost:8080/api/review/court
// {
//     "userId" : 11,
//     "courtId" : 22,
//     "content" : "구장이 좀 더러워요233",
//     "reviewRate" : 3
// }
