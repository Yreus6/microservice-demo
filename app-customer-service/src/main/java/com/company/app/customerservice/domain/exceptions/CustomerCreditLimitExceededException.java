package com.company.app.customerservice.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomerCreditLimitExceededException extends RuntimeException {
    
    public CustomerCreditLimitExceededException() {
        super("Credit limit exceeded");
    }
}
