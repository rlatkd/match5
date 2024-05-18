package site.match5.domain.stadium.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stadium {
    private Integer id;
    private String location;
    private Integer isParking;
    private Integer isToilet;
    private Integer isShower;
    private Integer isShoes;
    private String rentalIntro;
    private String name;
    private String lat;
    private String lng;

}
