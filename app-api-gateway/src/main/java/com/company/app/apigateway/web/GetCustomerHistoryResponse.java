package com.company.app.apigateway.web;

import com.company.app.orderservice.api.web.GetOrderResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.money.MonetaryAmount;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class GetCustomerHistoryResponse {
    
    private final Integer customerId;
    
    private final String name;
    
    private final MonetaryAmount creditLimit;
    
    private final List<GetOrderResponse> orders;
}
