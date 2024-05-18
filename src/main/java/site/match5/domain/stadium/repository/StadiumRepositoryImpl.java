package site.match5.domain.stadium.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.match5.domain.stadium.dto.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StadiumRepositoryImpl implements StadiumRepository {
    private final StadiumMapper dao;

    @Override
    public CourtInfoRes findCourtInfoById(Integer courtId) {
        return dao.findCourtInfoById(courtId);
    }

    @Override
    public List<StadiumNameAndLocRes> findAllStadium(int start, int end) {
        return dao.findAllStadium(start,end);
    }

    @Override
    public List<StadiumNameAndLocRes> findAllStadiumByLocation(String location,int start, int end) {
        return dao.findAllStadiumByLocation(location,start,end);
    }

    @Override
    public StadiumInfoRes findStadiumInfo(Integer stadiumId) {
        return dao.findStadiumInfo(stadiumId);
    }

    @Override
    public StadiumFacilityInfoRes findStadiumFacilityInfoById(Integer courtId) {
        return dao.findStadiumFacilityInfoById(courtId);
    }

    @Override
    public String findStadiumNoticeById(Integer courtId) {
        return dao.findStadiumNoticeById(courtId);
    }

    @Override
    public CourtInfoRes findCourtInfoByCourtReview(Integer courtReviewId) {
        return dao.findCourtInfoByCourtReview(courtReviewId);
    }

    @Override
    public List<StadiumInfoRes> findAllStadiumPosition() {
        return dao.findAllStadiumPosition();
    }

    @Override
    public StadiumInfoRes findStadiumPosition(Integer stadiumId) {
        return dao.findStadiumPosition(stadiumId);
    }

    @Override
    public StadiumPagingInfo findPagingInfo() {
        return dao.findPagingInfo();
    }

    @Override
    public List<Stadium> findAllStadiumInfo() {
        return dao.findAllStadiumInfo();
    }

    @Override
    public List<Stadium> findAllStadiumInfoByLocation(StadiumCond stadiumCond) {
        return dao.findAllStadiumInfoByLocation(stadiumCond);
    }

    @Override
    public StadiumFacilityInfoRes findStadiumFacilityInfoByStadiumId(Integer stadiumId) {
        return dao.findStadiumFacilityInfoByStadiumId(stadiumId);
    }

    @Override
    public String findStadiumNoticeByStadiumId(Integer stadiumId) {
        return dao.findStadiumNoticeByStadiumId(stadiumId);
    }

    @Override
    public List<StadiumInfoRes> findAllStadiumPositionByLocation(StadiumCond stadiumCond) {
        return dao.findAllStadiumPositionByLocation(stadiumCond);
    }


}
