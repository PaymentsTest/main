package com.paymentstest.dto;

import lombok.Data;

@Data
public class Amount {
    private Integer amount;
    private Integer taxFree;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer green_deposit;
}
