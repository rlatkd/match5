package site.match5.domain.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MidTemperatureDTO {
    @JsonProperty("response")
    private MidTemperatureDTO.Response response;

    @Getter
    public static class Response {
        @JsonProperty("body")
        private MidTemperatureDTO.Body body;
    }

    @Getter
    public static class Body {
        @JsonProperty("items")
        private MidTemperatureDTO.Items items;
    }

    @Getter
    public static class Items {
        @JsonProperty("item")
        private List<Item> item;
    }

    @Getter
    public static class Item {
        @JsonProperty("taMin3")
        private String taMin3;

        @JsonProperty("taMax3")
        private String taMax3;

        @JsonProperty("taMin4")
        private String taMin4;

        @JsonProperty("taMax4")
        private String taMax4;

        @JsonProperty("taMin5")
        private String taMin5;

        @JsonProperty("taMax5")
        private String taMax5;

        @JsonProperty("taMin6")
        private String taMin6;

        @JsonProperty("taMax6")
        private String taMax6;

        @JsonProperty("taMin7")
        private String taMin7;

        @JsonProperty("taMax7")
        private String taMax7;
    }
    public MidTemperatureDTO.Item getItem(){
        return this.response.body.items.item.get(0); // midTemperatureData.getResponse().getBody().getItems().getItem().get(0)
    }
}
