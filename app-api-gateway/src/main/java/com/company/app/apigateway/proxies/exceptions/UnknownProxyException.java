package com.company.app.apigateway.proxies.exceptions;

public class UnknownProxyException extends RuntimeException {
    
    public UnknownProxyException(String message) {
        super(message);
    }
}
