package com.paymentstest.service;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.paymentstest.dto.KaKaoPayReadyResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {
    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    static final String admin_Key = "DEVF8C8EE60B36736D708419C1A23553B4D0E704";
    private KaKaoPayReadyResponseDto kakaoResponseReady;

    public KaKaoPayReadyResponseDto kakaoPayReady() {
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);
        params.put("partner_order_id", "partner_order_id");
        params.put("partner_user_id", "가맹점 partner_user_id");
        params.put("item_name", "회색바지");
        params.put("quantity", "2000");
        params.put("total_amount", "200");
        params.put("tax_free_amount", "0");
        params.put("approval_url", "http://localhost:8080/success.html");
        params.put("cancel_url","http://localhost:8080/cancel.html");
        params.put("fail_url", "http://localhost:8080/fail.html");

        System.out.println(params);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        kakaoResponseReady = restTemplate.postForObject("https://open-api.kakaopay.com/online/v1/payment/ready", request, KaKaoPayReadyResponseDto.class);

        return kakaoResponseReady;
    }
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "SECRET_KEY " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/json");

        return httpHeaders;
    }
}
