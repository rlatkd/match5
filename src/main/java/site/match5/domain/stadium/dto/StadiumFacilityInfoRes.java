package site.match5.domain.stadium.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class StadiumFacilityInfoRes {
    private int isParking;
    private int isToilet;
    private int isShower;
    private int isShoes;
    private int sizeX;
    private int sizeY;
}
