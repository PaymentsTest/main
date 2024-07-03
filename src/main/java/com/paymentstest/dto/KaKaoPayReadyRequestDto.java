package com.paymentstest.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class KaKaoPayReadyRequestDto {

    //가맹점 코드, 10자
    private String cid;

    //가맹점 주문번호, 최대 100자
    private String partner_order_id;

    //가맹점 회원 id, 최대 100자
    private String partner_user_id;

    private String item_name;
    private int quantity;
    private int total_amount;
    private int tax_free_amount;

    //결제 성공 시 redirect url, 최대 255자
    private String approval_url;
    //결제 취소 시 redirect url, 최대 255자
    private String cancel_url;
    //결제 실패 시 redirect url, 최대 255자
    private String fail_url;
}
