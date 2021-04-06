package com.chany.mise.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.simple.parser.JSONParser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;

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


    public JSONObject toJSON(){
        JSONObject result = new JSONObject();
        try {
            JSONObject jobj = new JSONObject();
            jobj.put("미세먼지",pm10Grade);
            jobj.put("초미세먼지", pm25Grade);
            JSONParser parser = new JSONParser();
            result = (JSONObject) parser.parse(jobj.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


}
