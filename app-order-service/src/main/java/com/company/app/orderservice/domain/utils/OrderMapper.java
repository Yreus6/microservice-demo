package com.company.app.orderservice.domain.utils;

import com.company.app.orderservice.common.OrderDetails;
import com.company.app.orderservice.domain.dto.CreateOrderDto;
import com.company.app.orderservice.domain.entities.Order;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    
    private final ModelMapper mapper;
    
    public Order convertToEntity(CreateOrderDto createOrderDto) {
        OrderDetails orderDetails = mapper.map(createOrderDto, OrderDetails.class);
        orderDetails.setTotalPrice(FastMoney.of(
            new BigDecimal(createOrderDto.getTotalPrice()),
            "USD"
        ));
        
        return new Order(orderDetails);
    }
}
