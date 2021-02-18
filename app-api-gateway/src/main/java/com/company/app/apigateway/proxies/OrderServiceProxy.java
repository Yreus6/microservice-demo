package com.company.app.apigateway.proxies;

import com.company.app.apigateway.order.OrderDestinations;
import com.company.app.apigateway.proxies.exceptions.UnknownProxyException;
import com.company.app.orderservice.api.web.GetOrderResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class OrderServiceProxy {

    private final CircuitBreaker circuitBreaker;
    
    private final TimeLimiter timeLimiter;
    
    private final String orderServiceUrl;
    
    private final WebClient webClient;
    
    public OrderServiceProxy(
        CircuitBreakerRegistry circuitBreakerRegistry,
        TimeLimiterRegistry timeLimiterRegistry,
        OrderDestinations orderDestinations,
        WebClient webClient
    ) {
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("MY_CIRCUIT_BREAKER");
        this.timeLimiter = timeLimiterRegistry.timeLimiter("MY_TIME_LIMITER");
        this.orderServiceUrl = orderDestinations.getOrderServiceUrl();
        this.webClient = webClient;
    }
    
    public Mono<List<GetOrderResponse>> getOrdersByCustomerId(String customerId) {
        
        return webClient.get()
            .uri(orderServiceUrl + "/api/v1/orders/customers/{customerId}", customerId)
            .exchangeToMono(response -> {
                return switch (response.statusCode()) {
                    case OK -> response.bodyToMono(GetOrderResponse[].class).map(Arrays::asList);
                    default -> Mono.error(new UnknownProxyException("Unknown: " + response.statusCode()));
                };
            })
            .transformDeferred(TimeLimiterOperator.of(timeLimiter))
            .transformDeferred(CircuitBreakerOperator.of(circuitBreaker));
    }
}
