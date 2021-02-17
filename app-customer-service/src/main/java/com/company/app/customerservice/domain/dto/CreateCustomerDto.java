package com.company.app.customerservice.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class CreateCustomerDto {
    
    @NotNull
    @Length(min = 1)
    private final String name;
    
    @NotNull
    @Length(min = 1)
    private final String creditLimit;
}
