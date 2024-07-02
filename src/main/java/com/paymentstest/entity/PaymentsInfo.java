package com.paymentstest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class PaymentsInfo {

    @Id @GeneratedValue
    private Long id;
    private String partnerUserID;
    private String tid;

}
