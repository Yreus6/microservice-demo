package com.company.app.apigateway.proxies;

import com.company.app.apigateway.customer.CustomerConfiguration;
import com.company.app.apigateway.order.OrderConfiguration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;

@Configuration
@Import({
    CustomerConfiguration.class,
    OrderConfiguration.class
})
public class ProxyConfiguration {
    
    @Value("${apigateway.timeout.millis}")
    private long apiGatewayTimeoutMillis;
    
    @Bean
    public TimeLimiterRegistry timeLimiterRegistry() {
        return TimeLimiterRegistry.of(
            TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(apiGatewayTimeoutMillis))
                .build()
        );
    }
    
    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
            .timeLimiterConfig(
                TimeLimiterConfig.custom()
                    .timeoutDuration(Duration.ofMillis(apiGatewayTimeoutMillis))
                    .build()
            )
            .build()
        );
    }
}
