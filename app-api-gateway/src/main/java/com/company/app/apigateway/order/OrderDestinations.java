package com.company.app.apigateway.order;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "order.destinations")
@Data
public class OrderDestinations {

    @NotNull
    private String orderServiceUrl;
}
