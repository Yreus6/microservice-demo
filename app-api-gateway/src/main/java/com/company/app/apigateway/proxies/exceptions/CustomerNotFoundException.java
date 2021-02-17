package com.company.app.apigateway.proxies.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    
    public CustomerNotFoundException() {
        super("Could not find this customer");
    }
}
