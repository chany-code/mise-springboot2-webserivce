package com.chany.mise.springboot.service;

import com.chany.mise.springboot.api.GradeApiClient;
import com.chany.mise.springboot.domain.Station;
import com.chany.mise.springboot.domain.TmXY;
import com.chany.mise.springboot.web.dto.GradeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.IOException;

@RequiredArgsConstructor
@EnableAutoConfiguration
@Service
public class GradeService {

    //@Autowired
    private final GradeApiClient gradeApiClient;
/*
    public GradeResponseDto findByDong(String dong){
        int pm10;
        int pm25;

        return new GradeResponseDto(pm10, pm25);
    }
*/
    //근처의 '관측소명'으로 미세먼지 등급 구하기
    public String getGradeByStation(String station) throws Exception{
        //TmXY tmXY = new TmXY(dong);
        //Station station = new Station();
        return gradeApiClient.requestGrade(station);


    }


    //public StatonResponseDto

    //public TmXYResponseDto


}
