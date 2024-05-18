package site.match5.domain.client.util;

import java.util.HashMap;
import java.util.Map;

public class WeatherIconUtil {
    private static final Map<String, String> weatherIconMap = new HashMap<>();

    static {
        // SKY (하늘 상태) 값에 따른 아이콘 매핑
        weatherIconMap.put("맑음", "/images/weather/sun.png");
        weatherIconMap.put("맑고 비", "/images/weather/cloud-sun-rain.png");
        weatherIconMap.put("맑고 눈", "/images/weather/cloud-sun-rain.png");
        weatherIconMap.put("맑고 비/눈", "/images/weather/cloud-sun-rain.png");
        weatherIconMap.put("맑고 소나기", "/images/weather/cloud-sun-rain.png");

        weatherIconMap.put("구름많음", "/images/weather/clouds-and-sun.png");
        weatherIconMap.put("구름많고 비", "/images/weather/cloud-sun-rain.png");
        weatherIconMap.put("구름많고 눈", "/images/weather/cloud-sun-rain.png");
        weatherIconMap.put("구름많고 비/눈", "/images/weather/cloud-sun-rain.png");
        weatherIconMap.put("구름많고 소나기", "/images/weather/cloud-sun-rain.png");

        weatherIconMap.put("흐림", "/images/weather/greycloud.png");
        weatherIconMap.put("흐리고 비", "/images/weather/rainy.png");
        weatherIconMap.put("흐리고 눈", "/images/weather/rainy.png");
        weatherIconMap.put("흐리고 비/눈", "/images/weather/rainy.png");
        weatherIconMap.put("흐리고 소나기", "/images/weather/rainy.png");
    }

    public static String getWeatherIcon(String weatherDescription) {
        // 날씨 설명에 맞는 아이콘을 반환
        return weatherIconMap.getOrDefault(weatherDescription, "default-icon.png");
    }
}
