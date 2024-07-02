package com.paymentstest.controller;

import com.paymentstest.dto.KaKaoPayReadyResponseDto;
import com.paymentstest.service.KakaoPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/kakaopay")
public class KakaoPayController {

    @Autowired
    private KakaoPayService kakaoPayService;

    @PostMapping("/ready")
    public KaKaoPayReadyResponseDto kakaopayready() {
        return kakaoPayService.kakaoPayReady();
    }

}
