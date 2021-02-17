package com.company.app.customerservice.api.web;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.money.MonetaryAmount;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class GetCustomerResponse {
    
    private final Integer id;
    
    private final String name;
    
    private final MonetaryAmount creditLimit;
}
