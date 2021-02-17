package com.company.app.apigateway.common.swagger;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SwaggerController {
    
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('users')")
    public Mono<Map<String, Object>> hello(
        @AuthenticationPrincipal OAuth2User oAuth2User,
        @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", oAuth2User.getName());
        map.put("authorities", oAuth2User.getAuthorities());
        map.put("token", client.getAccessToken());
        
        return Mono.just(map);
    }
}
