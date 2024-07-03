package com.paymentstest.controller;

import com.paymentstest.dto.KaKaoPayApproveResponseDto;
import com.paymentstest.dto.KaKaoPayReadyResponseDto;
import com.paymentstest.dto.KakaopayCancelResponseDto;
import com.paymentstest.service.KakaoPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/kakaopay")
public class KakaoPayController {

    @Autowired
    private KakaoPayService kakaoPayService;

    @PostMapping("/ready")
    public KaKaoPayReadyResponseDto kakaopayReady() {
        return kakaoPayService.kakaoPayReady();
    }
    @PostMapping("/approve")
    public KaKaoPayApproveResponseDto kakaopayApprove(@RequestParam("pg_token") String pgToken) {
        log.info("pgToken={}", pgToken);
        return kakaoPayService.kakaoPayApprove(pgToken);
    }

    @PostMapping("/cancel")
    public KakaopayCancelResponseDto kakaopayCancel() {
        return kakaoPayService.kakaoPayCancel();
    }

}
