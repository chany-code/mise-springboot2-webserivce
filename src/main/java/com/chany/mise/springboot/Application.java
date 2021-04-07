package com.chany.mise.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//스프링부트의 자동 설정, 스프링 Bean 읽기와 생성 자동으로 설정. 해당 위치부터 설정을 읽어가므로 프로젝트 최상단에 위치
public class Application {//메인 클래스
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
