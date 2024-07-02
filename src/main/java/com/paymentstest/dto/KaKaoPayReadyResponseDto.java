package com.paymentstest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class KaKaoPayReadyResponseDto {

    //결제 고유 번호, 20자
    private String tid;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;

    private LocalDateTime created_at;
}