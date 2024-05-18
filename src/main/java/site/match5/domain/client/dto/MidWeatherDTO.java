package site.match5.domain.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MidWeatherDTO {
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
        @JsonProperty("wf3Am")
        private String wf3Am;

        @JsonProperty("wf3Pm")
        private String wf3Pm;

        @JsonProperty("wf4Am")
        private String wf4Am;

        @JsonProperty("wf4Pm")
        private String wf4Pm;

        @JsonProperty("wf5Am")
        private String wf5Am;

        @JsonProperty("wf5Pm")
        private String wf5Pm;

        @JsonProperty("wf6Am")
        private String wf6Am;

        @JsonProperty("wf6Pm")
        private String wf6Pm;

        @JsonProperty("wf7Am")
        private String wf7Am;

        @JsonProperty("wf7Pm")
        private String wf7Pm;
    }
    public MidWeatherDTO.Item getItem(){
        return this.response.body.items.item.get(0); //.wf3Pm or .getWf3Pm
    }

}
