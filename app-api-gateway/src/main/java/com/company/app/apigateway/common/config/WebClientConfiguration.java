package com.company.app.apigateway.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Import({
    CommonConfiguration.class
})
@RequiredArgsConstructor
public class WebClientConfiguration {
    
    private final ObjectMapper objectMapper;
    
    @Bean
    @LoadBalanced
    public WebClient webClient(
        ReactiveClientRegistrationRepository clientRegistrationRepository,
        ServerOAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
    ) {
        var oauth2 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
        oauth2.setDefaultOAuth2AuthorizedClient(true);
        oauth2.setDefaultClientRegistrationId("okta");
        
        return WebClient.builder().codecs(clientCodecConfigurer -> {
            clientCodecConfigurer.defaultCodecs().jackson2JsonDecoder(
                new Jackson2JsonDecoder(objectMapper)
            );
        })
            .filter(oauth2)
            .build();
    }
}
