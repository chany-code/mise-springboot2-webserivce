package com.chany.mise.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor//RequiredArgsConstructor
public class GradeResponseDto {
    private String pm10grade;//미세먼지 등급
    private String pm25grade;//초미세먼지 등급
    private String dong; //사용자 위치(00동)
    private String tmX; //tmX좌표
    private String tmY; //tmY좌표


}
