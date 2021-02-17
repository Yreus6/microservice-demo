package com.company.app.apigateway.main.config;

import com.company.app.apigateway.common.config.WebClientConfiguration;
import com.company.app.apigateway.proxies.ProxyConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.company.app.apigateway"})
@Import({
    WebClientConfiguration.class,
    ProxyConfiguration.class
})
public class GatewayConfiguration {

}
