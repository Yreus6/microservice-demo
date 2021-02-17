package com.company.app.apigateway.order;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableConfigurationProperties(OrderDestinations.class)
public class OrderConfiguration {
    
    @Bean
    public RouterFunction<ServerResponse> orderHistoryHandlerRouting(
        OrderHistoryHandlers orderHistoryHandler
    ) {
        return RouterFunctions.route(
            RequestPredicates.GET("/api/v1/customers/{customerId}/order-history"),
            orderHistoryHandler::getOrderHistory
        );
    }
    
    @Bean
    public RouteLocator orderProxyRouting(
        RouteLocatorBuilder builder,
        OrderDestinations orderDestinations,
        TokenRelayGatewayFilterFactory factory
    ) {
        return builder.routes()
            .route(r -> r.path("/api/v1/orders/customers/**")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.filter(factory.apply()))
                .uri(orderDestinations.getOrderServiceUrl())
            )
            .build();
    }
}
