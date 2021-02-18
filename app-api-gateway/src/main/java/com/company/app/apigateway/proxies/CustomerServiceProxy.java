package com.company.app.apigateway.proxies;

import com.company.app.apigateway.customer.CustomerDestinations;
import com.company.app.apigateway.proxies.exceptions.CustomerNotFoundException;
import com.company.app.apigateway.proxies.exceptions.UnknownProxyException;
import com.company.app.customerservice.api.web.GetCustomerResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomerServiceProxy {
    
    private final CircuitBreaker circuitBreaker;
    
    private final WebClient webClient;
    
    private final String customerServiceUrl;
    
    private final TimeLimiter timeLimiter;
    
    public CustomerServiceProxy(
        CircuitBreakerRegistry circuitBreakerRegistry,
        WebClient webClient,
        CustomerDestinations customerDestinations,
        TimeLimiterRegistry timeLimiterRegistry
    ) {
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("MY_CIRCUIT_BREAKER");
        this.webClient = webClient;
        this.customerServiceUrl = customerDestinations.getCustomerServiceUrl();
        this.timeLimiter = timeLimiterRegistry.timeLimiter("MY_TIME_LIMITER");
    }
    
    public Mono<GetCustomerResponse> getCustomerById(String customerId) {
        
        return webClient.get()
            .uri(customerServiceUrl + "/api/v1/customers/{customerId}", customerId)
            .exchangeToMono(response -> {
                return switch (response.statusCode()) {
                    case OK -> response.bodyToMono(GetCustomerResponse.class);
                    case NOT_FOUND -> Mono.error(new CustomerNotFoundException());
                    default -> Mono.error(
                        new UnknownProxyException("Unknown: " + response.statusCode())
                    );
                };
            })
            .transformDeferred(TimeLimiterOperator.of(timeLimiter))
            .transformDeferred(CircuitBreakerOperator.of(circuitBreaker));
    }
}
