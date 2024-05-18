package site.match5.domain.stadium.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StadiumInfoRes {
    private Integer id;
    private String name;
    private Float lat; // 위도
    private Float lng; //경도
    private List<String> images;
}
