package site.match5.domain.client.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeekWeatherDto {
    private String minTemp;
    private String maxTemp;
    private String weather;
    private String weatherIcon;
}
