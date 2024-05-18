package site.match5.domain.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@Getter
public class ShortWeatherDTO {

    @JsonProperty("response")
    private Response response;

    @Getter
    public static class Response {

        @JsonProperty("body")
        private Body body;
    }

    @Getter
    public static class Body {
        @JsonProperty("items")
        private Items items;
    }

    @Getter
    public static class Items {
        @JsonProperty("item")
        private List<Item> item;
    }

    @Getter
    public static class Item {
        @JsonProperty("category")
        private String category;

        @JsonProperty("fcstDate")
        private String fcstDate;

        @JsonProperty("fcstTime")
        private String fcstTime;

        @JsonProperty("fcstValue")
        private String fcstValue;
    }

    //[ {}, {}, {}, {} ... ] >> date는 동일
    public List<ShortWeatherDTO.Item> getItemsOfDate(String date){
        //return this.response.body.items.item.get(0).; //.category or .fcstDate ...
        return this.response.body.items.item.stream()
                .filter(i -> date.equals(i.getFcstDate()))
                .collect(Collectors.toList());
    }
    // 날짜별 데이터를 정리하는 메소드
    public static Map<String, Object> organizeDataByDate(List<ShortWeatherDTO.Item> itemsOfDate) { //getItemsOfDate(String date) >> items
        Map<String, Object> organizedData = new HashMap<>();

        for (ShortWeatherDTO.Item item : itemsOfDate) {
            String category = item.getCategory();
            String fcstValue = item.getFcstValue();

            if ("TMN".equals(category) || "TMX".equals(category)) {
                // TMN, TMX는 단일 값으로
                organizedData.put(category, fcstValue);
            } if("SKY".equals(category) || "PTY".equals(category)) {
                // SKY, PTY >> List로
                // 키 "category"가 Map에 없으면, 빈 ArrayList를 만들어서 해당 키에 추가
                organizedData.computeIfAbsent(category, k -> new ArrayList<String>());
                List<String> list = (List<String>) organizedData.get(category);
                list.add(fcstValue);
            }
        }

        return organizedData;
    }




}
