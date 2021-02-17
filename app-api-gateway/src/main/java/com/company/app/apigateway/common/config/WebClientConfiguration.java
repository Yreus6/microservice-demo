package com.company.app.apigateway.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Import({
    CommonConfiguration.class
})
@RequiredArgsConstructor
public class WebClientConfiguration {
    
    private final ObjectMapper objectMapper;
    
    @Bean
    public WebClient webClient() {
        return WebClient.builder().codecs(clientCodecConfigurer -> {
            clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(
                new Jackson2JsonDecoder(objectMapper)
            );
        }).build();
    }
}
