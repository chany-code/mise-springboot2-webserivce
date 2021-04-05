package com.chany.mise.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GradeResponseDto {
    private String pm10Grade;//미세먼지 등급
    private String pm25Grade;//초미세먼지 등급
    //private final String dong; //사용자 위치(00동)
    //private final String tmX; //tmX좌표
    //private final String tmY; //tmY좌표

    @Builder
    public GradeResponseDto(String pm10Grade, String pm25Grade){
        this.pm10Grade=pm10Grade;
        this.pm25Grade=pm25Grade;
    }

    @Override
    public String toString(){
        return "미세먼지 : "+pm10Grade+"\n초미세먼지 : "+pm25Grade;
    }


}
