package com.paymentstest.service;

import com.paymentstest.dto.KaKaoPayApproveResponseDto;
import com.paymentstest.dto.KaKaoPayReadyResponseDto;
import com.paymentstest.dto.KakaopayCancelResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
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
        params.put("item_name", "주히한테 치킨사주기");
        params.put("quantity", "2000");
        params.put("total_amount", "20000");
        params.put("tax_free_amount", "0");
        params.put("approval_url", "http://localhost:8080/kakaopay/approve");
        params.put("cancel_url","http://localhost:8080/cancel.html");
        params.put("fail_url", "http://localhost:8080/fail.html");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        kakaoResponseReady = restTemplate.postForObject("https://open-api.kakaopay.com/online/v1/payment/ready", request, KaKaoPayReadyResponseDto.class);

        return kakaoResponseReady;
    }

    public KaKaoPayApproveResponseDto kakaoPayApprove(String pgToken){
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);
        params.put("tid", kakaoResponseReady.getTid());
        params.put("partner_order_id", "partner_order_id");
        params.put("partner_user_id", "가맹점 partner_user_id");
        params.put("pg_token", pgToken);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();
        KaKaoPayApproveResponseDto kakaoApprove = restTemplate.postForObject("https://open-api.kakaopay.com/online/v1/payment/approve", request, KaKaoPayApproveResponseDto.class);

        return kakaoApprove;

    }


    public KakaopayCancelResponseDto kakaoPayCancel() {
        Map<String, String> params = new HashMap<>();
        params.put("cid", cid);
        params.put("tid", kakaoResponseReady.getTid());
        params.put("cancel_amount", "20000");
        params.put("cancel_tax_free_amount", "0");

        log.info("TID {}", kakaoResponseReady.getTid());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        KakaopayCancelResponseDto kakaoCancelResponse =
                restTemplate.postForObject("https://open-api.kakaopay.com/online/v1/payment/cancel", request, KakaopayCancelResponseDto.class);

        return kakaoCancelResponse;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "SECRET_KEY " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/json");

        return httpHeaders;
    }
}
