// $('.owl-carousel-peerreview').owlCarousel({
// 	loop: false,
// 	center: true,
// 	items: 2,
// 	margin: 30,
// 	nav: false,
// 	navText: [
// 		'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(-1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>',
// 		'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>'
// 	],
// 	autoplay: false,
// 	autoplayHoverPause: false,
// 	responsive: {
// 		600: {
// 			items: 2
// 		}
// 	}
// })

let usersInfoData;	//유저들 정보
let courtData;	//구장 정보

let currentUserId;
let matchHistoryId
const nullUserImg = '/images/utils/nullUserImg.png';
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

const userIdList = [0];	//본인 제외 다른 유저의 id

const loadPage = async () => {
	const urlParams = new URLSearchParams(window.location.search);
	matchHistoryId = parseInt(urlParams.get('matchHistoryId'));

	await getData();
	await insertUserData();
	await insertCourtData();

	$('.owl-carousel-peerreview').owlCarousel({
		loop: false,
		center: true,
		items: 2,
		margin: 30,
		nav: false,
		navText: [
			'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(-1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>',
			'<svg fill="#a6a6a6" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" stroke="#a6a6a6" transform="matrix(1, 0, 0, 1, 0, 0)"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M256,0C114.837,0,0,114.837,0,256s114.837,256,256,256s256-114.837,256-256S397.163,0,256,0z M335.083,271.083 L228.416,377.749c-4.16,4.16-9.621,6.251-15.083,6.251c-5.461,0-10.923-2.091-15.083-6.251c-8.341-8.341-8.341-21.824,0-30.165 L289.835,256l-91.584-91.584c-8.341-8.341-8.341-21.824,0-30.165s21.824-8.341,30.165,0l106.667,106.667 C343.424,249.259,343.424,262.741,335.083,271.083z"></path> </g> </g> </g></svg>'
		],
		autoplay: false,
		autoplayHoverPause: false,
		responsive: {
			600: {
				items: 2
			}
		}
	})
}

const getData = async () => {
	//더미 데이터
	// matchHistoryId = 815;
	// usersInfoData = {
	// 	"hteamManagerId": 167,
	// 	"ateamManagerId": 5440,
	// 	"hteamUsersInfo": [
	// 		{
	// 			"id": 1819,
	// 			"levelId": 4,
	// 			"age": 36,
	// 			"nickname": "잠실일짱심미정",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 167,
	// 			"levelId": 4,
	// 			"age": 42,
	// 			"nickname": "윤예원",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 4584,
	// 			"levelId": 4,
	// 			"age": 47,
	// 			"nickname": "백제고분일짱최순옥",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 3306,
	// 			"levelId": 1,
	// 			"age": 49,
	// 			"nickname": "역삼일짱박춘자",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 7939,
	// 			"levelId": 5,
	// 			"age": 46,
	// 			"nickname": "백제고분일짱윤아름",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		}
	// 	],
	// 	"ateamUsersInfo": [
	// 		{
	// 			"id": 5440,
	// 			"levelId": 1,
	// 			"age": 44,
	// 			"nickname": "허미영",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 2066,
	// 			"levelId": 1,
	// 			"age": 37,
	// 			"nickname": "잠실일짱박은주",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 6234,
	// 			"levelId": 3,
	// 			"age": 40,
	// 			"nickname": "양재천일짱손예원",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 6480,
	// 			"levelId": 4,
	// 			"age": 34,
	// 			"nickname": "서은영",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		},
	// 		{
	// 			"id": 6695,
	// 			"levelId": 2,
	// 			"age": 40,
	// 			"nickname": "반포대일짱손현주",
	// 			"imageUrl": null,
	// 			"matchCount": 0
	// 		}
	// 	]
	// }
	// currentUserId = 6695;
	// 현재 사용자 user정보

	//현재 유저 ID
	const userRawInfo = await fetch('/api/auth/kakao/ids');
	const userInfo = await userRawInfo.json();
	currentUserId = await userInfo.userId;

	//매칭된 유저 정보
	const userRawData = await fetch(`/api/matched/users/detail/${matchHistoryId}`);
	usersInfoData = await userRawData.json();

	//매칭된 구장 정보
	const courtRawData = await fetch(`/api/court/${matchHistoryId}`);
	courtData = await courtRawData.json();

	// currentUserId = await usersInfoData.hteamManagerId; //테스트용 (주석 처리 필요)
	// console.log(currentUserId)
}

const insertUserData = () => {
	$$item = document.querySelectorAll('.carousel-wrap .owl-carousel .item');

	let itemIndex = 0;
	usersInfoData.hteamUsersInfo.forEach((userInfo) => {
		if (userInfo.id !== currentUserId) {
			updateCarouselItemContainer($$item[itemIndex++], userInfo, "home");
			userIdList.push(userInfo.id);
		}
	})
	usersInfoData.ateamUsersInfo.forEach((userInfo) => {
		if (userInfo.id !== currentUserId) {
			updateCarouselItemContainer($$item[itemIndex++], userInfo, "away");
			userIdList.push(userInfo.id);
		}
	})
}

const updateCarouselItemContainer = ($item, userInfo, team) => {
	$item.classList.add(team);
	$item.querySelector('.carousel-item-name p').textContent = userInfo.nickname;
	$item.querySelector('.matchgameinfo__userprofile img').src = userInfo.imageUrl === null ? nullUserImg: userInfo.imageUrl;

	$$moreInfoContainer = $item.querySelectorAll('.more-info .container');
	$$moreInfoContainer[0].querySelector('.more-info-value p').textContent = team === "home" ? "H" : "A";
	$$moreInfoContainer[1].querySelector('.more-info-value img').src = levelImg[userInfo.levelId];
	$$moreInfoContainer[1].querySelector('.more-info-title').textContent = levelName[userInfo.levelId];
	$$moreInfoContainer[2].querySelector('.more-info-value p').textContent = userInfo.matchCount;
}

const insertCourtData = () => {
	const $$contentMainStartformDiv = document.querySelectorAll('.content__main__startform .content__main__info-container > div');
	$$contentMainStartformDiv[1].textContent = courtData.name;
	$$contentMainStartformDiv[2].textContent = dateConvertor(courtData.scheduleDate, courtData.startT);
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

loadPage();


const peerReviewData = {
	user1: "none",
	user2: "none",
	user3: "none",
	user4: "none",
	user5: "none",
	user6: "none",
	user7: "none",
	user8: "none",
	user9: "none"
}

const rate = {
	bad: -1,
	soso: 1,
	good: 2
}


const $$carouselWrapRadioTypeInput = document.querySelectorAll('.carousel-wrap input[type="radio"]')
$$carouselWrapRadioTypeInput.forEach(($carouselWrapRadioTypeInput) => {
	$carouselWrapRadioTypeInput.addEventListener('change', (event) => {
		if(event.target.checked) {
			peerReviewData[event.target.name] = event.target.id.split('-')[1];
		}
	})
})

const $startformSubmitContainer = document.querySelector('.startform__submit-container button');
$startformSubmitContainer.addEventListener('click', async (event) => {
	event.preventDefault();

	const postData = [];
	for (i = 1; i <= 9; i++) {
		if (peerReviewData["user" + i] === "none") continue;

		const data = {
			"fromUserId": currentUserId,
			"toUserId": userIdList[i],
			"matchingHistoryId": matchHistoryId,
			"reviewRate": rate[peerReviewData["user" + i]]
		}

		postData.push(data);
	}
	console.log(postData)
	await postReviewUserAll(postData);
	window.location.href = `/match_info_detail?matchHistoryId=${matchHistoryId}`;
})

const postReviewUserAll = async (data) => {
	try {
		const res = await fetch('/api/review/user/all', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		});
		const msg = await res.json();
		console.log("응답메시지 - ", msg);
	} catch (err) {
		console.log(err);
	}
}