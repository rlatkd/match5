package site.match5.domain.stadium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.match5.global.validation.annotation.Location;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StadiumCond {
    @Location
    private String location;
}
