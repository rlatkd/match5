package site.match5.domain.stadium.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourtInfoRes {
    private String name;
    private Float lat; // 위도
    private Float lng; //경도
    private List<String> images;
}
