package com.chany.mise.springboot.service;

import com.chany.mise.springboot.api.GradeApiClient;
import com.chany.mise.springboot.domain.Station;
import com.chany.mise.springboot.domain.TmXY;
import com.chany.mise.springboot.web.dto.GradeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@EnableAutoConfiguration
@Service
public class GradeService {

    //@Autowired
    private final GradeApiClient gradeApiClient;
    private final TmXYService tmXYService;
    private final StationService stationService;


    //근처의 '관측소명'으로 미세먼지 등급 구하기
    public GradeResponseDto getGradeByStation(Station station) {
        return gradeApiClient.requestGrade(station);
    }

    //동 이름 -> tmXY 좌표 -> 측정소 -> Grade
    public String getGradeByDong(String dong){
        TmXY tmXY = tmXYService.findXYByDong(dong);
        Station station = stationService.findStationByXY(tmXY);
        GradeResponseDto gradeResponseDto = getGradeByStation(station);

        return gradeResponseDto.toString();
    }

}
