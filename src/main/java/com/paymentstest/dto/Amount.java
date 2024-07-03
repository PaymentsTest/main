package com.paymentstest.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class Amount {
    private Integer amount;
    private Integer taxFree;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer green_deposit;
}
