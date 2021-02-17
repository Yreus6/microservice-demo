package com.company.app.orderservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
    
    public OrderNotFoundException() {
        super("Could not find this order");
    }
}
