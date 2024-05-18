// document.addEventListener("DOMContentLoaded", function() {
//     let itemsPerPage = 5; // 페이지당 아이템 수
//     let currentPage = 1; // 현재 페이지
//     let totalItems = 0; // 전체 아이템 수
//     let totalPages = 0; // 전체 페이지 수
//
//     // 페이지 버튼 클릭 시 해당 페이지의 목록 표시
//     document.addEventListener('click', function(event) {
//         if (event.target.classList.contains('page-buttons')) {
//             let pageNum = parseInt(event.target.innerText);
//             fetchData(pageNum);
//         }
//     });
//
//     // 이전 버튼 클릭 시 이전 페이지로 이동
//     document.querySelector('.prev-btn').addEventListener('click', function() {
//         if (currentPage > 1) {
//             fetchData(currentPage - 1);
//         }
//     });
//
//     // 다음 버튼 클릭 시 다음 페이지로 이동
//     document.querySelector('.next-btn').addEventListener('click', function() {
//         if (currentPage < totalPages) {
//             fetchData(currentPage + 1);
//         }
//     });
//
//     // 페이지를 기반으로 데이터 가져오기
//     function fetchData(pageNum) {
//         // API 호출하여 데이터 가져오기
//         fetch('your_api_endpoint_here?page=' + pageNum + '&per_page=' + itemsPerPage)
//             .then(response => response.json())
//             .then(data => {
//                 // 데이터 성공적으로 가져왔을 때 처리
//                 totalItems = data.total_items;
//                 totalPages = Math.ceil(totalItems / itemsPerPage);
//                 currentPage = pageNum;
//
//                 // 페이지네이션 업데이트
//                 updatePagination();
//
//                 // 데이터 표시
//                 renderData(data.data);
//             })
//             .catch(error => {
//                 // 에러 처리
//                 console.error('API 호출 에러:', error);
//             });
//     }
//
//
//     // 페이지네이션 업데이트 함수
//     function updatePagination() {
//         // 페이지 버튼 업데이트
//         let pageButtons = document.querySelectorAll('.page-buttons button');
//         pageButtons.forEach(function(button, index) {
//             button.innerText = index + 1;
//             if (index + 1 === currentPage) {
//                 button.classList.add('active');
//             } else {
//                 button.classList.remove('active');
//             }
//         });
//
//         // 이전 버튼 활성화 여부
//         document.querySelector('.prev-btn').disabled = currentPage === 1;
//
//         // 다음 버튼 활성화 여부
//         document.querySelector('.next-btn').disabled = currentPage === totalPages;
//     }
//
//     // 데이터 표시 함수
//     function renderData(data) {
//         let reviewList = document.querySelector(".review-list-container");
//         reviewList.innerHTML = ""; // 이전의 목록 삭제
//
//         // 데이터를 기반으로 목록 생성 및 추가
//         data.forEach(function(item) {
//             let itemElement = document.createElement('div');
//             itemElement.classList.add('item');
//             itemElement.innerHTML = '<div class="index">' + item.index + '</div>' +
//                 '<div class="stadium_name">' + item.stadium_name + '</div>' +
//                 '<div class="location">' + item.location + '</div>';
//             reviewList.appendChild(itemElement);
//         });
//     }
//
//     // 초기 페이지 로드
//     fetchData(currentPage);
// });


//--------------------------------------------------------------------------------







// 페이지 넘버(처음 들어왔을 땐 1페이지)
let pageNum = 1;
let stadiumList;

//인덱스용 넘버
const itemsPerPage = 5;

const getStadiumList = async () => {
    try {
        const res = await fetch(`/api/stadium/all/${pageNum}/${itemsPerPage}`);
        stadiumList = await res.json();
        console.log("[stadiumList]", stadiumList);
        renderData(stadiumList);
    } catch (err) {
        console.log(err);
        throw err;
    }
}

const renderData = (stadiumList) => {
    let reviewList = $(".review-list-container");

    //목록 초기화
    reviewList.empty();

    //목록 생성
    const startIndex = (pageNum - 1) * itemsPerPage; //시작 인덱스 계산
    for (let i = 0; i < itemsPerPage; i++) {
        let itemIndex = startIndex + i;
        let item = stadiumList[i];
        let itemElement = $("<div>").addClass("item");

        //item 엘리멘트의 데이터셋을 추가

        if (item) { //데이터가 있는 경우
            itemElement.html(`
                <div class="index">${itemIndex + 1}</div>
                <div class="stadium_name">${item.name}</div>
                <div class="location">${item.location}</div>
            `);

            //아이템 클릭 시 이동 이벤트 추가
            itemElement.click(() => {
                // 클릭된 아이템의 stadiumId를 가져와 URL 구성
                const stadiumId = item.stadiumId;
                const url = `/stadium-list/detail?stadiumId=${stadiumId}`;

                // 페이지 이동
                window.location.href = url;
            });
        } else { //데이터가 없는 경우
            itemElement.html(`
                <div class="index"></div>
                <div class="stadium_name"></div>
                <div class="location"></div>
            `);
        }
        reviewList.append(itemElement);
    }
}

getStadiumList();

document.addEventListener("DOMContentLoaded", () => {

    //페이지가 로드되었을 때 첫 번째 페이지 버튼에 클릭된 색상 적용
    document.querySelector('.page-buttons button:first-child').style.backgroundColor = 'green';
    document.querySelector('.page-buttons button:first-child').style.color = 'white';

    //페이지 버튼 클릭 시 해당 페이지의 목록 표시
    document.querySelector('.page-buttons').addEventListener('click', (e) => {
        if (e.target.tagName === 'BUTTON') {
            pageNum = parseInt(e.target.innerText);
            getStadiumList();

            //모든 버튼의 배경색 초기화
            document.querySelectorAll('.page-buttons button').forEach(button => {
                button.style.backgroundColor = '';
                button.style.color = '';
            });

            //클릭된 버튼의 배경색을 녹색으로 변경
            e.target.style.backgroundColor = 'green';
            e.target.style.color = 'white';
        }
    });
});