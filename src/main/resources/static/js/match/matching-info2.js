// //API에서 데이터를 가져온다고 가정
// const fetchingLeft = () => {
//     return new Promise((resolve, reject) => {
//         //가정-1초뒤에 데이터가 왔음
//         setTimeout(() =>
//             resolve(
//         {
//             "hteamManagerId": 4,
//             "ateamManagerId": 10,
//             "hteamUsersInfo": [
//                 {
//                     "id": 3,
//                     "levelId": 2,
//                     "age": 25,
//                     "nickname": "김111",
//                     "imageUrl": "/images/userprofile/user1.jpg",
//                     "matchCount": 1
//                 },
//                 {
//                     "id": 4,
//                     "levelId": 2,
//                     "age": 48,
//                     "nickname": "김222",
//                     "imageUrl": "/images/userprofile/user2.jpg",
//                     "matchCount": 2
//                 },
//                 {
//                     "id": 1,
//                     "levelId": 3,
//                     "age": 48,
//                     "nickname": "김333",
//                     "imageUrl": "/images/userprofile/user3.jpg",
//                     "matchCount": 3
//                 },
//                 {
//                     "id": 10,
//                     "levelId": 4,
//                     "age": 39,
//                     "nickname": "김444",
//                     "imageUrl": "/images/userprofile/user4.jpg",
//                     "matchCount": 4
//                 },
//                 {
//                     "id": 2,
//                     "levelId": 4,
//                     "age": 25,
//                     "nickname": "김555",
//                     "imageUrl": "/images/userprofile/user5.jpg",
//                     "matchCount": 5
//                 }
//             ],
//             "ateamUsersInfo": [
//                 {
//                     "id": 2,
//                     "levelId": 4,
//                     "age": 25,
//                     "nickname": "이111",
//                     "imageUrl": "/images/userprofile/user1.jpg",
//                     "matchCount": 1
//                 },
//                 {
//                     "id": 4,
//                     "levelId": 2,
//                     "age": 29,
//                     "nickname": "이222",
//                     "imageUrl": "/images/userprofile/user2.jpg",
//                     "matchCount": 2
//                 },
//                 {
//                     "id": 10,
//                     "levelId": 3,
//                     "age": 20,
//                     "nickname": "이333",
//                     "imageUrl": "/images/userprofile/user3.jpg",
//                     "matchCount": 3
//                 },
//                 {
//                     "id": 7,
//                     "levelId": 1,
//                     "age": 46,
//                     "nickname": "이444",
//                     "imageUrl": "/images/userprofile/user4.jpg",
//                     "matchCount": 4
//                 },
//                 {
//                     "id": 6,
//                     "levelId": 10,
//                     "age": 34,
//                     "nickname": "이555",
//                     "imageUrl": "/images/userprofile/user5.jpg",
//                     "matchCount": 5
//                 }
//             ]
//         })
//         , 1000);
//     });
// }
//
//
// //레벨id에 해당하는 image 파일명 파싱
// const getLevelImage = (levelId) => {
//     switch (levelId) {
//         case 1:
//             return "bronze.png";
//         case 2:
//             return "silver.png";
//         case 3:
//             return "gold.png";
//         case 4:
//             return "platinum.png";
//         case 5:
//             return "diamond.png";
//         default:
//             return "";
//     }
// }
//
// //레벨id에 해당하는 이름 파싱
// const getLevelName = (levelId) => {
//     switch (levelId) {
//         case 1:
//             return "BRONZE";
//         case 2:
//             return "SILVER";
//         case 3:
//             return "GOLD";
//         case 4:
//             return "PLATINUM";
//         case 5:
//             return "DIAMOND";
//         default:
//             return "";
//     }
// }
//
// const getLeftData = async () => {
//     try {
//         const leftData = await fetchingLeft();
//
//         // HOME 팀 사용자 정보 추가
//         const homeTeamContainer = document.querySelector('.content__main__left .match-team-container:nth-child(1)');
//         leftData.hteamUsersInfo.forEach(user => {
//             const userCard = createUserCard(user, 'home', leftData);
//             homeTeamContainer.appendChild(userCard);
//         });
//
//         // AWAY 팀 사용자 정보 추가
//         const awayTeamContainer = document.querySelector('.content__main__left .match-team-container:nth-child(2)');
//         leftData.ateamUsersInfo.forEach(user => {
//             const userCard = createUserCard(user, 'away', leftData);
//             awayTeamContainer.appendChild(userCard);
//         });
//     } catch (err) {
//         console.log(err);
//         throw err;
//     }
// }
//
// // 사용자 정보 카드 생성 함수
// const createUserCard = (user, team, leftData) => {
//     const card = document.createElement('div');
//     card.classList.add('match-userinfo-card', team); // HOME 팀이나 AWAY 팀에 따라 클래스 추가
//     const additionalDiv = document.createElement('div');
//     additionalDiv.classList.add('additional');
//     const userCardDiv = document.createElement('div');
//     userCardDiv.classList.add('user-card');
//     const userProfileDiv = document.createElement('div');
//     userProfileDiv.classList.add('matchgameinfo__userprofile');
//     const userProfileImg = document.createElement('img');
//     userProfileImg.src = user.imageUrl;
//     userProfileImg.alt = '';
//
//     const moreInfoDiv = document.createElement('div');
//     moreInfoDiv.classList.add('more-info');
//     const containerDiv1 = createContainerDiv('more-info-value', 'more-info-title', 'PLATINUM', user.levelId);
//     const containerDiv2 = createContainerDiv('more-info-value', 'more-info-title', user.matchCount, '경기수');
//     const containerDiv3 = createContainerDiv('more-info-value', 'more-info-title', user.age, '나이');
//
//     const generalDiv = document.createElement('div');
//     generalDiv.classList.add('general');
//     const containerDiv4 = document.createElement('div');
//     containerDiv4.classList.add('container');
//     const platinumImg = document.createElement('img');
//     platinumImg.src = '/images/utils/platinum.png';
//     platinumImg.alt = '';
//     const nicknameP = document.createElement('p');
//     nicknameP.textContent = user.nickname;
//
//     const managerImg = document.createElement('img');
//     managerImg.classList.add('manager');
//     if (user.id === leftData.hteamManagerId || user.id === leftData.ateamManagerId) {
//         managerImg.classList.add('manager-checked');
//     }
//     managerImg.src = '/images/utils/manager.png';
//     managerImg.alt = '';
//
//     containerDiv4.appendChild(platinumImg);
//     containerDiv4.appendChild(nicknameP);
//     generalDiv.appendChild(containerDiv4);
//     generalDiv.appendChild(managerImg);
//
//     userProfileDiv.appendChild(userProfileImg);
//     userCardDiv.appendChild(userProfileDiv);
//     moreInfoDiv.appendChild(containerDiv1);
//     moreInfoDiv.appendChild(containerDiv2);
//     moreInfoDiv.appendChild(containerDiv3);
//
//     additionalDiv.appendChild(userCardDiv);
//     additionalDiv.appendChild(moreInfoDiv);
//
//     card.appendChild(additionalDiv);
//     card.appendChild(generalDiv);
//
//     return card;
// }
//
// // 사용자 정보를 담는 컨테이너와 타이틀을 생성하는 함수
// const createContainerDiv = (valueClass, titleClass, value, title) => {
//     const containerDiv = document.createElement('div');
//     containerDiv.classList.add('container');
//     const valueDiv = document.createElement('div');
//     valueDiv.classList.add(valueClass);
//     const valueElement = document.createElement('p');
//     valueElement.textContent = value;
//     const titleDiv = document.createElement('div');
//     titleDiv.classList.add(titleClass);
//     titleDiv.textContent = title;
//
//     valueDiv.appendChild(valueElement);
//     containerDiv.appendChild(valueDiv);
//     containerDiv.appendChild(titleDiv);
//
//     return containerDiv;
// }
//
// document.addEventListener('DOMContentLoaded', (event) => {
//     // 페이지의 DOM이 로드된 후 실행될 코드 작성
//     getLeftData(); // 또는 다른 필요한 함수 호출
// });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
// //API에서 데이터를 가져온다고 가정
// const fetchingRight = () => {
//     return new Promise((resolve, reject) => {
//         //가정-1초뒤에 데이터가 왔음
//         setTimeout(() =>
//             resolve({
//                 "courtId": 24,
//                 "name": "노원하라A",
//                 "images": [
//                 "https://d31wz4d3hgve8q.cloudfront.net/media/coner_U7jEVnu.jpg",
//                 "https://d31wz4d3hgve8q.cloudfront.net/media/goalline_55nKjvh.jpg",
//                 "https://d31wz4d3hgve8q.cloudfront.net/media/halfline_JuMQ19j.jpg"
//             ],
//                 "scheduleDate": "2024-05-24T00:00:00",
//                 "startT": "16",
//                 "endT": "18",
//                 "productPrice": 11000,
//
//                 //좌표값으로 location 가져오는거 필요함
//                 "location": "서울 용산구 한강대로23길 55 (한강로3가) HDC아이파크몰 리빙파크 8F / 9F / 10F",
//
//
//                 "lat": "127.0626195",
//                 "lng": "37.64680948"
//             }), 1000);
//     });
// }
//
// //stadiumTitle: "용산 아이파크몰 (7구장)",
// //schedule: "2024년 05월 04일 (토요일) 14:00 ~ 16:00",
// //location: "서울 용산구 한강대로23길 55 (한강로3가) HDC아이파크몰 리빙파크 8F / 9F / 10F",
// //cost: "11,000원 / 1인"
//
// const getRightData = async () => {
//     try {
//         const rightData = await fetchingRight(); //원래는 res = await fetch({API_URL}) / rightData = res.json();
//         console.log("rightData - ", rightData)
//
//         //시작시간 및 종료시간 형식 변환
//         const startDate = new Date(rightData.scheduleDate);
//         const startTime = rightData.startT ? rightData.startT.padStart(2, "0") + ":00" : "";
//         const endTime = rightData.endT ? rightData.endT.padStart(2, "0") + ":00" : "";
//
//         //날짜 및 시간 형식 변환
//         const formattedDate = `${startDate.getFullYear()}-${(startDate.getMonth() + 1).toString().padStart(2, "0")}-${startDate.getDate().toString().padStart(2, "0")}`;
//         const formattedTime = `${startTime} ~ ${endTime}`;
//
//         //가격 형식으로 변환
//         const formattedPrice = rightData.productPrice.toLocaleString("ko-KR") + " 원 / 1인";
//
//         document.getElementById("stadium-title-placeholder").innerText = rightData.name;
//         document.getElementById("content-schedule-placeholder").innerText = `${formattedDate} (${getDayOfWeek(startDate)}) ${formattedTime}`;
//         document.getElementById("location-placeholder").innerText = rightData.location;
//         document.getElementById("cost-info-placeholder").innerText = `${formattedPrice}`
//     } catch (err) {
//         console.log(err);
//         throw err;
//     }
// }
//
// //요일 가져옴
// const getDayOfWeek = (date) => {
//     const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
//     return daysOfWeek[date.getDay()];
// }
//
//
// getRightData();