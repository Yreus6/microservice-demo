package com.company.app.apigateway.customer;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@EnableConfigurationProperties(CustomerDestinations.class)
public class CustomerConfiguration {
    
    @Bean
    public RouteLocator customerProxyRouting(
        RouteLocatorBuilder builder,
        CustomerDestinations customerDestinations,
        TokenRelayGatewayFilterFactory factory
    ) {
        return builder.routes()
            .route(r -> r.path("/api/v1/customers/**")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.filter(factory.apply()))
                .uri(customerDestinations.getCustomerServiceUrl())
            )
            .build();
    }
}
