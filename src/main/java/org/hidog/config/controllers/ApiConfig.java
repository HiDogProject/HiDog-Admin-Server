package org.hidog.config.controllers;

import lombok.Data;

@Data
public class ApiConfig {
    private String publicOpenApiKey; // 공공 API 인증키

    private String kakaoJavascriptKey; // 카카오 API - 자바스크립트 앱 키
    private String tmapJavascriptKey; //Tmap API 자바스크립트 앱 키

    private String checkoutApiKey; // 이니시스 결제 키
}
