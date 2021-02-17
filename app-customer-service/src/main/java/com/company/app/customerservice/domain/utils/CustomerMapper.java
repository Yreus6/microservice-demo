package com.company.app.customerservice.domain.utils;

import com.company.app.customerservice.domain.dto.CreateCustomerDto;
import com.company.app.customerservice.domain.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    
    private final ModelMapper mapper;
    
    public Customer convertToEntity(CreateCustomerDto createCustomerDto) {
        Customer customer = mapper.map(createCustomerDto, Customer.class);
        customer.setCreditLimit(FastMoney.of(
            new BigDecimal(createCustomerDto.getCreditLimit()),
            "USD"
        ));
        customer.setCreditReservations(Collections.emptyMap());
        
        return customer;
    }
}
