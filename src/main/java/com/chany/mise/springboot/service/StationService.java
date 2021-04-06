package com.chany.mise.springboot.service;

import com.chany.mise.springboot.api.StationApiClient;
import com.chany.mise.springboot.domain.Station;
import com.chany.mise.springboot.domain.TmXY;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StationService {
    private final StationApiClient stationApiClient;

    //tm xy좌표로 측정소 이름 구하기
    public Station findStationByXY(TmXY tmXY){
        String stationName = "";
        stationName = stationApiClient.getStationByXY(tmXY).getStationName();
        //객체로
        return new Station(stationName);
    }
}
