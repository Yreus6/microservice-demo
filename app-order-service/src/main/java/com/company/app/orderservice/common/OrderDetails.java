package com.company.app.orderservice.common;

import com.company.app.common.converter.MonetaryAmountConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Embeddable
public class OrderDetails {
    
    @Column(name = "customer_id")
    private Integer customerId;
    
    @Column(name = "total_price")
    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount totalPrice;
}
