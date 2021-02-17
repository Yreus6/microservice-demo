package com.company.app.customerservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    
    public CustomerNotFoundException() {
        super("Could not find this customer");
    }
}
