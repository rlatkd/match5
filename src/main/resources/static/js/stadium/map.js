//지도 띄우기
document.addEventListener("DOMContentLoaded", function() {
    //풋살장 데이터 불러오기
    async function fetchStadiumList(){
        try{
            const response = await fetch(`/api/stadium/all/latlng`);
            const data = await response.json();//<List<StadiumInfoRes>>
            data.forEach(d => console.log("json 데이터 불러오기 성공? : ", d));

            //

            return data.map(stadium => ({
                id: stadium.id,
                title: stadium.name,
                lng: stadium.lng,
                lat: stadium.lat,
                image: stadium.images[0],
                name: stadium.name,
            }));
        }catch(err){
            console.error(err);
            throw err;
        }
    }


    // 모달을 표시하는 함수
    document.getElementById("map-mode-button").addEventListener("click", async function() {
        document.getElementById("mapModal").style.display = "flex"; // 모달을 보이도록 변경
        document.getElementById("mapModal").style.justifyContent = "center";
        document.getElementById("mapModal").style.alignItems = "center";

        try {
            const positions = await fetchStadiumList();
            // 카카오맵 API를 불러오기 위해 $.getScript 사용 >> api 가 로드된 후에 모달에 지도를 표시한다.
            $.getScript(
                "https://dapi.kakao.com/v2/maps/sdk.js?appkey=9d11cde4f718e89925dd0395e42ba477&libraries=services&autoload=false",
                () => {
                    kakao.maps.load(function () {

                        // 모달이 표시된 후에 카카오맵을 표시하는 코드
                        const container = document.getElementById('map');
                        const options = {
                            center: new kakao.maps.LatLng(37.529057, 127.11612),
                            level: 10
                        };
                        const map = new kakao.maps.Map(container, options);
                        // map.relayout();
                        const bounds = new kakao.maps.LatLngBounds();
                        const geocoder = new kakao.maps.services.Geocoder();
                        function searchDetailAddrFromCoords(coords, callback) {
                            // 좌표로 법정동 상세 주소 정보를 요청합니다
                            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
                        }
                        // 마커 이미지의 이미지 주소입니다
                        let imageSrc = "images/stadium/marker.png";
                        // 마커 이미지의 이미지 크기 입니다
                        let imageSize = new kakao.maps.Size(24, 35);
                        // 마커 이미지를 생성합니다
                        let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                        for (let i = 0; i < positions.length; i++) {
                            console.log("positions 만들어졌니? >> ", positions[i].image);
                            let kakaoPosition = new kakao.maps.LatLng(positions[i].lng, positions[i].lat);
                            bounds.extend(kakaoPosition);
                            let address;
                            searchDetailAddrFromCoords(kakaoPosition, function(result, status){
                                if (status === kakao.maps.services.Status.OK) {
                                    address = result[0].address.address_name;

                                    //마커 생성 위치 이동
                                    // 마커를 생성합니다
                                    let marker = new kakao.maps.Marker({
                                        map: map,
                                        position: kakaoPosition, // 마커를 표시할 위치
                                        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                                        clickable: true,
                                        // image: markerImage // 마커 이미지
                                    });

                                    //이미지, content
                                    //커스텀 오버레이의 컨텐츠
                                    let content = `
                                                <div class="overlaybox-wrapper">
                                                    <div class="overlaybox">
                                                        <div class="boximage" style="background-image: url('${positions[i].image}');"></div>
                                                        <div class="boxtitle">${positions[i].name}</div>
                                                        <div class="boxtext">
                                                            <div class="boxtext-title">[주소]</div>
                                                            <div class="boxaddress">${address}</div>
                                                        </div>
                                                        <div class="boxlink-wrapper" data-stadium-id=String(${positions[i].id})>
                                                            <div class="boxlink-text">상세정보</div>
                                                        </div>
                                                    </div>
                                                </div>`;

                                    // DOMParser를 사용하여 content를 파싱하고 data-stadium-id 속성을 추가
                                    // const parser = new DOMParser();
                                    // const doc = parser.parseFromString(content, 'text/html');
                                    const boxlinkWrapper = document.querySelector('.boxlink-wrapper'); //왜 null..? 아 content에 넣기 전..임
                                    // boxlinkWrapper.setAttribute('data-stadium-id', positions[i].id);
                                    // content = boxlinkWrapper.outerHTML;

                                    document.addEventListener('click', function(event){
                                        if(event.target.classList.contains('boxlink-wrapper')){
                                            const stadiumId = event.target.dataset.stadiumId;
                                            const newUrl = `stadium-list/stadium_detail?stadiumId=${stadiumId}`; //가상의 경로
                                            window.location.href = newUrl;
                                        }

                                    })

                                    // 커스텀 오버레이를 생성합니다 (표시)
                                    let customOverlay = new kakao.maps.CustomOverlay({
                                        // 지도 위에 표시하게 하는 속성 : map
                                        // map:map,
                                        position: marker.getPosition(),
                                        clickable: true,
                                        content: content, //개별적으로 설정
                                        xAnchor: 0.5,
                                        yAnchor: 1.12,
                                        zIndex: 1000
                                    });

                                    kakao.maps.event.addListener(marker, 'click', function() {
                                        customOverlay.setMap(map);
                                        map.panTo(marker.getPosition());
                                    });
                                    kakao.maps.event.addListener(map, 'click', function() {
                                        customOverlay.setMap(null);
                                    });
                                }//if
                            });



                        } //풋살장 리스트 for문
                        map.setBounds(bounds);
                    }); //kakao.map.load()
                }
            ); //getScript
        }catch(err){
            console.log("Failed to fetch stadium list~~:", err)
        }
    }); //button event

    //------json data 불러오기
    //    @GetMapping("/all/latlng")
    //     ResponseEntity<List<StadiumInfoRes>> findAllStadiumPosition() {
    //         return ResponseEntity.ok(stadiumService.findAllStadiumPosition());
    //     }


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





