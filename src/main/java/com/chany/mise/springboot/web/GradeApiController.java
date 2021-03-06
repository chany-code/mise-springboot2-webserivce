package com.chany.mise.springboot.web;

import com.chany.mise.springboot.service.GradeService;
import com.chany.mise.springboot.service.TmXYService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController//JSON반환
public class GradeApiController{

    private final GradeService gradeService;
    private final TmXYService tmXYService;

    @GetMapping("/api/miseGrade")
    public JSONObject getGradeByDong(@RequestParam(value = "dong")String dong) {
        System.out.println("@RequestParam 동 이름 : "+dong);
        return gradeService.getGradeByDong(dong);
    }
    /* Test
    @GetMapping("/tmxy/{dong}")
    public String findXYByDong(@PathVariable String dong){return tmXYService.findXYByDong(dong);}
    */

}
