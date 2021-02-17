package com.company.app.orderservice.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class CreateOrderDto {
    
    @NotNull
    private final Integer customerId;
    
    @NotNull
    @Length(min = 1)
    private final String totalPrice;
}
