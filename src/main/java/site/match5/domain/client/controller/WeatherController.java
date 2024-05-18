package site.match5.domain.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.match5.domain.client.dto.MidTemperatureDTO;
import site.match5.domain.client.dto.MidWeatherDTO;
import site.match5.domain.client.dto.ShortWeatherDTO;
import site.match5.domain.client.dto.WeekWeatherDto;
import site.match5.domain.client.service.WeatherService;
import site.match5.domain.client.util.WeatherIconUtil;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) { //서비스 의존성 주입
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ResponseEntity<List<WeekWeatherDto>> getWeather(Model model) throws URISyntaxException {
        LocalDate today = LocalDate.now();
        LocalDate dayAfter1 = today.plusDays(1); // 1일 추가
        LocalDate dayAfter2 = today.plusDays(2); // 2일 추가
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String base_date = today.format(dateFormatter); //"20240510"
        String dayAfter1Str = dayAfter1.format(dateFormatter); //"20240510"
        String dayAfter2Str = dayAfter2.format(dateFormatter); //"20240510"
        String tmFc = base_date + "0600"; //"202405100600"
        //String tmFc = "202405100600"; // 발표 시각(일 2회) 06:00,18:00
        //String base_date = "20240510"; //일자 cf. Base_time : 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)

        String serviceKey = "alS76nwteUpcE1O2nv%2FYBA7QGGnTv%2BCIuzw4Jg3mvRSyJqCwPllNuPOfW3OQc%2FZZR5vNRFPGFhkKU%2FB6S1HkVA%3D%3D"; // 외부 API의 서비스 키

//        midWeatherDTO midWeatherData = weatherService.getMidLandFcst(serviceKey, tmFc); //중기육상예보조회(3일후~ 오전/오후 날씨 예보, 강수확률)
//        MidTemperatureDTO midTemperatureData = weatherService.getMidTa(serviceKey, tmFc); //중기기온조회(3일후~ 최저/최고 기온)
        ShortWeatherDTO shortWeatherData = weatherService.getVilageFcst(serviceKey, base_date); //단기예보조회 (내일, 모레)

        MidWeatherDTO.Item midWeatherData = weatherService.getMidLandFcst(serviceKey, tmFc).getItem(); //중기육상예보조회(3일후~ 오전/오후 날씨 예보, 강수확률)
        MidTemperatureDTO.Item midTemperatureData = weatherService.getMidTa(serviceKey, tmFc).getItem(); //중기기온조회(3일후~ 최저/최고 기온)

        //model.addAttribute("midWeatherData", midWeatherData.getResponse().getBody().getItems().getItem().get(0));
        //model.addAttribute("midWeatherData", midWeatherData.getItem());
        //model.addAttribute("midTemperatureData", midTemperatureData.getItem());
        //model.addAttribute("shortWeatherData", shortWeatherData.getResponse().getBody().getItems().getItem());

        //wf3, tmn3.. 전부 개별로 넘기기
        String wf3Pm = midWeatherData.getWf3Pm();
        String taMin3 = midTemperatureData.getTaMin3();
        String taMax3 = midTemperatureData.getTaMax3();
        String wf3Icon = WeatherIconUtil.getWeatherIcon(wf3Pm);

        String wf4Pm = midWeatherData.getWf4Pm();
        String taMin4 = midTemperatureData.getTaMin4();
        String taMax4 = midTemperatureData.getTaMax4();
        String wf4Icon = WeatherIconUtil.getWeatherIcon(wf4Pm);

        String wf5Pm = midWeatherData.getWf5Pm();
        String taMin5 = midTemperatureData.getTaMin5();
        String taMax5 = midTemperatureData.getTaMax5();
        String wf5Icon = WeatherIconUtil.getWeatherIcon(wf5Pm);

        String wf6Pm = midWeatherData.getWf6Pm();
        String taMin6 = midTemperatureData.getTaMin6();
        String taMax6 = midTemperatureData.getTaMax6();
        String wf6Icon = WeatherIconUtil.getWeatherIcon(wf6Pm);

        String wf7Pm = midWeatherData.getWf7Pm();
        String taMin7 = midTemperatureData.getTaMin7();
        String taMax7 = midTemperatureData.getTaMax7();
        String wf7Icon = WeatherIconUtil.getWeatherIcon(wf7Pm);

//        model.addAttribute("wf3Pm", wf3Pm);
//        model.addAttribute("wf4Pm", wf4Pm);
//        model.addAttribute("wf5Pm", wf5Pm);
//        model.addAttribute("wf6Pm", wf6Pm);
//        model.addAttribute("wf7Pm", wf7Pm);
//        model.addAttribute("taMin3", taMin3);
//        model.addAttribute("taMin4", taMin4);
//        model.addAttribute("taMin5", taMin5);
//        model.addAttribute("taMin6", taMin6);
//        model.addAttribute("taMin7", taMin7);
//        model.addAttribute("taMax3", taMax3);
//        model.addAttribute("taMax4", taMax4);
//        model.addAttribute("taMax5", taMax5);
//        model.addAttribute("taMax6", taMax6);
//        model.addAttribute("taMax7", taMax7);
//        model.addAttribute("wf3Icon", wf3Icon);
//        model.addAttribute("wf4Icon", wf4Icon);
//        model.addAttribute("wf5Icon", wf5Icon);
//        model.addAttribute("wf6Icon", wf6Icon);
//        model.addAttribute("wf7Icon", wf7Icon);


        //----------------------------

        Map<String, Object> DayAfter1WeatherData = shortWeatherData.organizeDataByDate(shortWeatherData.getItemsOfDate(dayAfter1Str));
        Map<String, Object> DayAfter2WeatherData = shortWeatherData.organizeDataByDate(shortWeatherData.getItemsOfDate(dayAfter2Str));
        //1,2일 후 TMN, TMX
        String tmn1 = (String) DayAfter1WeatherData.get("TMN");
        String tmx1 = (String) DayAfter1WeatherData.get("TMX");
        String tmn2 = (String) DayAfter2WeatherData.get("TMN");
        String tmx2 = (String) DayAfter2WeatherData.get("TMX");

//        model.addAttribute("tmn1", tmn1);
//        model.addAttribute("tmx1", tmx1);
//        model.addAttribute("tmn2", tmn2);
//        model.addAttribute("tmx2", tmx2);


        //1일 후 SKY, PTY
        List<String> sky1List = (List<String>) DayAfter1WeatherData.get("SKY");
        String sky1 = findModeValue(sky1List);
        List<String> pty1List = (List<String>) DayAfter1WeatherData.get("PTY");
        String pty1 = findModeValue(pty1List);

        //2일 후 SKY, PTY
        List<String> sky2List = (List<String>) DayAfter2WeatherData.get("SKY");
        String sky2 = findModeValue(sky2List);
        List<String> pty2List = (List<String>) DayAfter2WeatherData.get("PTY");
        String pty2 = findModeValue(pty2List);

        String wf1 = getWf(sky1, pty1);
        String wf2 = getWf(sky2, pty2);

//        model.addAttribute("wf1", wf1);
//        model.addAttribute("wf2", wf2);

        String wf1Icon = WeatherIconUtil.getWeatherIcon(wf1);
        String wf2Icon = WeatherIconUtil.getWeatherIcon(wf2);

//        model.addAttribute("wf1Icon", wf1Icon);
//        model.addAttribute("wf2Icon", wf2Icon);

        List<WeekWeatherDto> weatherList = new ArrayList<>();
        WeekWeatherDto weekWeatherDto1 = new WeekWeatherDto();
        weekWeatherDto1.setWeather(wf1);
        weekWeatherDto1.setMinTemp(tmn1);
        weekWeatherDto1.setMaxTemp(tmx1);
        weekWeatherDto1.setWeatherIcon(wf1Icon);
        weatherList.add(weekWeatherDto1);

        WeekWeatherDto weekWeatherDto2 = new WeekWeatherDto();
        weekWeatherDto2.setWeather(wf2);
        weekWeatherDto2.setMinTemp(tmn2);
        weekWeatherDto2.setMaxTemp(tmx2);
        weekWeatherDto2.setWeatherIcon(wf2Icon);
        weatherList.add(weekWeatherDto2);

        WeekWeatherDto weekWeatherDto3 = new WeekWeatherDto();
        weekWeatherDto3.setWeather(wf3Pm);
        weekWeatherDto3.setMinTemp(taMin3);
        weekWeatherDto3.setMaxTemp(taMax3);
        weekWeatherDto3.setWeatherIcon(wf3Icon);
        weatherList.add(weekWeatherDto3);

        WeekWeatherDto weekWeatherDto4 = new WeekWeatherDto();
        weekWeatherDto4.setWeather(wf4Pm);
        weekWeatherDto4.setMinTemp(taMin4);
        weekWeatherDto4.setMaxTemp(taMax4);
        weekWeatherDto4.setWeatherIcon(wf4Icon);
        weatherList.add(weekWeatherDto4);

        WeekWeatherDto weekWeatherDto5= new WeekWeatherDto();
        weekWeatherDto5.setWeather(wf5Pm);
        weekWeatherDto5.setMinTemp(taMin5);
        weekWeatherDto5.setMaxTemp(taMax5);
        weekWeatherDto5.setWeatherIcon(wf5Icon);
        weatherList.add(weekWeatherDto5);

        WeekWeatherDto weekWeatherDto6 = new WeekWeatherDto();
        weekWeatherDto6.setWeather(wf6Pm);
        weekWeatherDto6.setMinTemp(taMin6);
        weekWeatherDto6.setMaxTemp(taMax6);
        weekWeatherDto6.setWeatherIcon(wf6Icon);
        weatherList.add(weekWeatherDto6);

        WeekWeatherDto weekWeatherDto7= new WeekWeatherDto();
        weekWeatherDto7.setWeather(wf7Pm);
        weekWeatherDto7.setMinTemp(taMin7);
        weekWeatherDto7.setMaxTemp(taMax7);
        weekWeatherDto7.setWeatherIcon(wf7Icon);
        weatherList.add(weekWeatherDto7);



        return ResponseEntity.ok(weatherList);
    }

    //SKY, TMP 로직
    //최빈값 구하기
    public static String findModeValue(List<String> data) {
        if (data == null || data.isEmpty()) {
            return "데이터 없음"; // 데이터가 없을 경우 기본값 반환
        }

        // 빈도 카운트를 위한 맵
        Map<String, Integer> frequencyMap = new HashMap<>();

        // 빈도 계산
        for (String value : data) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        // 최빈값을 찾기 위해 가장 높은 빈도와 해당 값
        int maxFrequency = 0;
        String mode = null;

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }

    //SKY, TMP 텍스트로 (중기 데이터와 같은 형식으로) 변환
    public String getWf(String sky, String pty) {
        if (pty.equals("0")) {
            switch (sky) {
                case "1":
                    return "맑음";
                case "3":
                    return "구름많음";
                case "4":
                    return "흐림";
                default:
                    return "";
            }
        } else { //pty : 1,2,3,4
            sky = switch (sky) {
                case "1" -> "맑고";
                case "3" -> "구름많고";
                case "4" -> "흐리고";
                default -> "";
            };
            pty = switch (pty) {
                case "1" -> "비";
                case "2" -> "비/눈";
                case "3" -> "눈";
                case "4" -> "소나기";
                default -> "";
            };
            return sky + " " + pty;
        }
    }


}

