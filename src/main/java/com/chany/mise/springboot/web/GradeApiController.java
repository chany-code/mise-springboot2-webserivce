package com.chany.mise.springboot.web;

import com.chany.mise.springboot.service.GradeService;
import com.chany.mise.springboot.web.dto.GradeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.IOException;

@RequiredArgsConstructor
@RestController//JSON반환
public class GradeApiController{

    private final GradeService gradeService;

    @GetMapping("/miseGrade/{station}")
    public String getGradeByDong(@PathVariable String station) throws Exception {
        return gradeService.getGradeByStation(station);
    }
    /*
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"); //URL
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=Mkm%2FJ0IVq25CKomnlLXR7Q%2Bp8rokqqZWe0HjNbDcMcM2U7Heo%2B9iReiJssVBsGRdzSDNkUV%2FBcPNfZ7aJIKiCw%3D%3D"); //Service Key
            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); //리턴 타입(xml, json)
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //한 페이지 결과 수: 하나의 결과, 즉 제일 최근의 결과만 출력함
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
            urlBuilder.append("&" + URLEncoder.encode("stationName", "UTF-8") + "=" + URLEncoder.encode(dong, "UTF-8")); //측정소 이름
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
            //StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line+"\n");
            }
            rd.close();
            conn.disconnect();
            //System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result + " ";
        */
/*

    @GetMapping("/tmXY/{dong}")
    public String findTmXY(@PathVariable String dong){
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getTMStdrCrdnt"); //*URL
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=Mkm%2FJ0IVq25CKomnlLXR7Q%2Bp8rokqqZWe0HjNbDcMcM2U7Heo%2B9iReiJssVBsGRdzSDNkUV%2FBcPNfZ7aJIKiCw%3D%3D"); ///*Service Key
            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("umdName", "UTF-8") + "=" + URLEncoder.encode(dong, "UTF-8"));
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
            //StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line+"\n");
            }
            rd.close();
            conn.disconnect();
            //System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result + " ";
    }

    @GetMapping("/station/{tmx}/{tmy}")
    public String getNearbyStation(@PathVariable String tmx, @PathVariable String tmy){
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getNearbyMsrstnList");
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=Mkm%2FJ0IVq25CKomnlLXR7Q%2Bp8rokqqZWe0HjNbDcMcM2U7Heo%2B9iReiJssVBsGRdzSDNkUV%2FBcPNfZ7aJIKiCw%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("tmX", "UTF-8") + "=" + URLEncoder.encode(tmx, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("tmY", "UTF-8") + "=" + URLEncoder.encode(tmy, "UTF-8"));
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
            //StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line+"\n");
            }
            rd.close();
            conn.disconnect();
            //System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result + " ";
    }
*/
}
