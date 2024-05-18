package site.match5.domain.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.match5.domain.client.dto.MidTemperatureDTO;
import site.match5.domain.client.dto.MidWeatherDTO;
import site.match5.domain.client.dto.ShortWeatherDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;

    //주입
    @Autowired
    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public MidWeatherDTO getMidLandFcst(String serviceKey, String tmFc) throws URISyntaxException { //중기육상예보조회(3일후~ 오전/오후 날씨 예보, 강수확률)
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst");
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(serviceKey);
        urlBuilder.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=10");
        urlBuilder.append("&").append(URLEncoder.encode("pageNo", StandardCharsets.UTF_8)).append("=1");
        urlBuilder.append("&").append(URLEncoder.encode("dataType", StandardCharsets.UTF_8)).append("=JSON");
        urlBuilder.append("&").append(URLEncoder.encode("regId", StandardCharsets.UTF_8)).append("=11B00000"); //11B00000
        urlBuilder.append("&").append(URLEncoder.encode("tmFc", StandardCharsets.UTF_8)).append("=").append(tmFc);
        System.out.println(urlBuilder.toString());
        URI uri = new URI(urlBuilder.toString());
        return restTemplate.getForObject(uri, MidWeatherDTO.class);
        //return restTemplate.getForObject(uri, String.class);
        //다받은 후, DTO에 필요한 값만 넣을 것
    }

    public MidTemperatureDTO getMidTa(String serviceKey, String tmFc) throws URISyntaxException { //중기기온조회(3일후~ 최저/최고 기온)
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa");
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(serviceKey);
        urlBuilder.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=10");
        urlBuilder.append("&").append(URLEncoder.encode("pageNo", StandardCharsets.UTF_8)).append("=1");
        urlBuilder.append("&").append(URLEncoder.encode("dataType", StandardCharsets.UTF_8)).append("=JSON");
        urlBuilder.append("&").append(URLEncoder.encode("regId", StandardCharsets.UTF_8)).append("=11B10101"); //11B10101
        urlBuilder.append("&").append(URLEncoder.encode("tmFc", StandardCharsets.UTF_8)).append("=").append(tmFc);
        System.out.println(urlBuilder.toString());
        URI uri = new URI(urlBuilder.toString());
        return restTemplate.getForObject(uri, MidTemperatureDTO.class);
    }

    public ShortWeatherDTO getVilageFcst(String serviceKey, String base_date) throws URISyntaxException { //단기예보조회 (내일, 모레)
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst");
        urlBuilder.append("?").append(URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)).append("=").append(serviceKey);
        urlBuilder.append("&").append(URLEncoder.encode("numOfRows", StandardCharsets.UTF_8)).append("=1000");
        urlBuilder.append("&").append(URLEncoder.encode("pageNo", StandardCharsets.UTF_8)).append("=1");
        urlBuilder.append("&").append(URLEncoder.encode("dataType", StandardCharsets.UTF_8)).append("=JSON");
        urlBuilder.append("&").append(URLEncoder.encode("base_date", StandardCharsets.UTF_8)).append("=").append(base_date);
        urlBuilder.append("&").append(URLEncoder.encode("base_time", StandardCharsets.UTF_8)).append("=0500");
        urlBuilder.append("&").append(URLEncoder.encode("nx", StandardCharsets.UTF_8)).append("=60");
        urlBuilder.append("&").append(URLEncoder.encode("ny", StandardCharsets.UTF_8)).append("=127");
        System.out.println(urlBuilder.toString());
        URI uri = new URI(urlBuilder.toString());
        return restTemplate.getForObject(uri, ShortWeatherDTO.class);
    }
}


