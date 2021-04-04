package com.chany.mise.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//@RequiredArgsConstructor
@RestController//JSON반환
public class GradeApiController {
/*
    @GetMapping("/api/v1/miseGrade/{dong}")
    public GradeResponseDto findByDong(@PathVariable String dong) {return miseGradeService.findByDong(dong)}
*/
    @GetMapping("/miseGrade")
    public String callapihttp(){

        StringBuffer result = new StringBuffer();
        try {
            String urlstr = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?"+
                    "returnType=json"+
                    "&numOfRows=1" +
                    "&pageNo=1" +
                    "&stationName=종로" +
                    "&dataTerm=DAILY"+
                    "&ver=1.0"+
                    "&serviceKey=Mkm%2FJ0IVq25CKomnlLXR7Q%2Bp8rokqqZWe0HjNbDcMcM2U7Heo%2B9iReiJssVBsGRdzSDNkUV%2FBcPNfZ7aJIKiCw%3D%3D";

            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while ((returnLine= br.readLine()) !=null){
                result.append(returnLine+"\n");
            }

            urlConnection.disconnect();


        }catch (Exception e){
            e.printStackTrace();
        }
        return result+"</xmp>";

    }

}
