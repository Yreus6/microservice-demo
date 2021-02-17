package com.company.app.orderservice.api.web;

import com.company.app.orderservice.api.common.OrderState;
import com.company.app.orderservice.api.common.RejectionReason;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.money.MonetaryAmount;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class GetOrderResponse {
    
    private final Integer id;
    
    private final Integer customerId;
    
    private final MonetaryAmount totalPrice;
    
    private final OrderState state;
    
    private final RejectionReason rejectionReason;
}
