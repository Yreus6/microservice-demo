package com.company.app.apigateway.customer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "customer.destinations")
@Data
public class CustomerDestinations {

    @NotNull
    private String customerServiceUrl;
}
