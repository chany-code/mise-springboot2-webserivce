package com.chany.mise.springboot.api;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@NoArgsConstructor
@Service
public class GradeApiClient {
    /* 시행착오
    private RestTemplate restTemplate;

    private final String url_getGrade = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=Mkm%2FJ0IVq25CKomnlLXR7Q%2Bp8rokqqZWe0HjNbDcMcM2U7Heo%2B9iReiJssVBsGRdzSDNkUV%2FBcPNfZ7aJIKiCw%3D%3D&returnType=json&numOfRows=1&pageNo=1&stationName={station}&dataTerm=DAILY&ver=1.0";

    public GradeResponseDto requestGrade(String station){
        HttpHeaders requestHeaders = new HttpHeaders();
        final HttpEntity<String> entity = new HttpEntity<>(requestHeaders);
        return restTemplate.exchange(url_getGrade, HttpMethod.GET,entity,GradeResponseDto.class,station).getBody();
    }
    */

    public String requestGrade(String station) {
        //StringBuffer result = new StringBuffer();
        String result = "";
        String pm10 = "";
        String pm25 = "";
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"); //URL
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=Mkm%2FJ0IVq25CKomnlLXR7Q%2Bp8rokqqZWe0HjNbDcMcM2U7Heo%2B9iReiJssVBsGRdzSDNkUV%2FBcPNfZ7aJIKiCw%3D%3D"); //Service Key
            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); //리턴 타입(xml, json)
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //한 페이지 결과 수: 하나의 결과, 즉 제일 최근의 결과만 출력함
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
            urlBuilder.append("&" + URLEncoder.encode("stationName", "UTF-8") + "=" + URLEncoder.encode(station, "UTF-8")); //측정소 이름
            urlBuilder.append("&" + URLEncoder.encode("dataTerm", "UTF-8") + "=" + URLEncoder.encode("DAILY", "UTF-8")); //요청 데이터기간 (하루 : DAILY, 한달 : MONTH, 3달 : 3MONTH)
            urlBuilder.append("&" + URLEncoder.encode("ver", "UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); //버전별 상세 결과 참고문서 참조
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                //result.append(line+"\n");
                result=result.concat(line);
            }

            //JSON Parser 만들어 문자열 데이터 객체화하기
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            JSONObject obj2 = (JSONObject)obj.get("response");
            JSONObject obj3 = (JSONObject) obj2.get("body");
            JSONArray array = (JSONArray) obj3.get("items");

            JSONObject value = (JSONObject) array.get(0);

            String pm10Grade = value.get("pm10Grade").toString();
            String pm25Grade = value.get("pm25Grade").toString();

            switch (pm10Grade){
                case "1" : pm10 = "좋음";
                break;
                case "2" : pm10 = "보통";
                break;
                case "3" : pm10 = "나쁨";
                break;
                case "4" : pm10 = "매우나쁨";
                break;
            }
            switch (pm25Grade){
                case "1" : pm25 = "좋음";
                    break;
                case "2" : pm25 = "보통";
                    break;
                case "3" : pm25 = "나쁨";
                    break;
                case "4" : pm25 = "매우나쁨";
                    break;
            }

            rd.close();
            conn.disconnect();
            //System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "미세먼지 : "+ pm10 + "\n초미세먼지 : "+ pm25;
    }


    //사용자의 위치(읍,면,동)로 tmX, tmY 좌표를 가져오는 기능
    public String requestTmXY(String dong){
        return "";
    }


}
