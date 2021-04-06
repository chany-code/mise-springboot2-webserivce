package com.chany.mise.springboot.api;

import com.chany.mise.springboot.domain.TmXY;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@NoArgsConstructor
@Service
public class TmXYApiClient {

    public TmXY findXYByDong(String dong){
        //StringBuffer result = new StringBuffer();
        String result = "";
        String tmx = "";
        String tmy = "";
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getTMStdrCrdnt"); //*URL
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=Mkm%2FJ0IVq25CKomnlLXR7Q%2Bp8rokqqZWe0HjNbDcMcM2U7Heo%2B9iReiJssVBsGRdzSDNkUV%2FBcPNfZ7aJIKiCw%3D%3D"); ///*Service Key
            urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));//중복된 동이름에 대한 filtering은 불가
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
                //result.append(line+"\n");
                result=result.concat(line);
            }
            rd.close();
            conn.disconnect();

            //JSON Parser 만들어 문자열 데이터 객체화하기
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result);
            JSONObject obj2 = (JSONObject)obj.get("response");
            JSONObject obj3 = (JSONObject) obj2.get("body");
            JSONArray array = (JSONArray) obj3.get("items");

            JSONObject value = (JSONObject) array.get(0);

            tmx = value.get("tmX").toString();
            tmy = value.get("tmY").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TmXY(tmx,tmy);
    }
}
