package com.paymentstest.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class KakaopayCancelResponseDto {

    //결제 고유 번호, 20자
    private String tid;
    private String partner_order_id;
    private String partner_user_id;

    private Amount amount;
    private Amount approvedCanceledAmount;
    private Amount canceledAmount;
    private Amount cancelAvailableAmount;

}
