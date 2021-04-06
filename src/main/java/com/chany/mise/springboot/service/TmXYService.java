package com.chany.mise.springboot.service;

import com.chany.mise.springboot.api.TmXYApiClient;
import com.chany.mise.springboot.domain.TmXY;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TmXYService {

    private final TmXYApiClient tmXYApiClient;

    public TmXY findXYByDong(String dong){
        String tmx = tmXYApiClient.findXYByDong(dong).getTmx();
        String tmy= tmXYApiClient.findXYByDong(dong).getTmy();
        //여기서 변수 초기화 =""; 해야할까요? 아니면 바로 tmXYApiClient.findXYByDong(dong).getTmx(); 붙여서 선언해도 될까요?

        return new TmXY(tmx,tmy);

    }

}
