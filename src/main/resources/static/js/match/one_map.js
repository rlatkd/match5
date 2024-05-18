//지도 띄우기 http://localhost:8080/match_info?matchingHistory = ?
document.addEventListener("DOMContentLoaded", function() {
    //풋살장 데이터 불러오기
    async function fetchOneStadium(){
        try{
            let currentURL = window.location.href;
            let urlParams = new URLSearchParams(window.location.search);
            let id = urlParams.get('matchHistoryId');
            console.log(id);

            const response = await fetch(`/api/court/${id}`);
            const data = await response.json();//<List<StadiumInfoRes>>
            console.log("json 데이터 불러오기 성공? : ", data[0]);
            console.log("fetch한 데이터입니다",data);
            return data;

        }catch(err){
            console.error(err);
            throw err;
        }
    }
    //fetchOneStadium();

    // 모달을 표시하는 함수
    document.getElementById("map-mode-button").addEventListener("click", async function() {
        document.getElementById("mapModal").style.display = "flex"; // 모달을 보이도록 변경
        document.getElementById("mapModal").style.justifyContent = "center";
        document.getElementById("mapModal").style.alignItems = "center";

        try {
            const oneStadium = await fetchOneStadium(); //{'key':value, ...}

            // 카카오맵 API를 불러오기 위해 $.getScript 사용 >> api 가 로드된 후에 모달에 지도를 표시한다.
            $.getScript(
                "https://dapi.kakao.com/v2/maps/sdk.js?appkey=9d11cde4f718e89925dd0395e42ba477&libraries=services&autoload=false",
                () => {
                    kakao.maps.load(function () {

                        // 모달이 표시된 후에 카카오맵을 표시하는 코드
                        const container = document.getElementById('map');
                        const options = {
                            center: new kakao.maps.LatLng(oneStadium.lng, oneStadium.lat),
                            level: 3
                        };
                        const map = new kakao.maps.Map(container, options);
                        // map.relayout();
                        const geocoder = new kakao.maps.services.Geocoder();
                        function searchDetailAddrFromCoords(coords, callback) {
                            // 좌표로 법정동 상세 주소 정보를 요청합니다
                            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
                        }
                        console.log("positions 만들어졌니? >> ", oneStadium);
                        let kakaoPosition = new kakao.maps.LatLng(oneStadium.lng, oneStadium.lat);
                        let address;
                        searchDetailAddrFromCoords(kakaoPosition, function(result, status){
                            if (status === kakao.maps.services.Status.OK) {
                                address = result[0].address.address_name;

                                //마커 생성 위치 이동
                                // 마커를 생성합니다
                                let marker = new kakao.maps.Marker({
                                    map: map,
                                    position: kakaoPosition, // 마커를 표시할 위치
                                    title: oneStadium.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                    clickable: true,
                                    // image: markerImage // 마커 이미지
                                });

                            }//if
                        });
                    }); //kakao.map.load()
                }
            ); //getScript
        }catch(err){
            console.log("Failed to fetch stadium list~~:", err)
        }
    }); //button event

    // 모달을 닫는 함수
    function closeModal() {
        document.getElementById("mapModal").style.display = "none"; // 모달을 숨김
    }

    // X 버튼 클릭 시 모달을 닫음
    document.getElementById("closeModal").addEventListener("click", closeModal);

    // 모달 바깥 영역을 클릭했을 때 모달을 닫는 로직
    window.addEventListener("click", function(event) {
        if (event.target == document.getElementById("mapModal")) { // 모달 바깥 영역을 클릭했을 때만 모달을 닫습니다.
            closeModal();
        }
    });
}); //Domloaded





