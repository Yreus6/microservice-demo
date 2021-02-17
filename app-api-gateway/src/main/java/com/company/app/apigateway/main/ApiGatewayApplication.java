package com.company.app.apigateway.main;

import com.company.app.apigateway.main.config.GatewayConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({GatewayConfiguration.class})
public class ApiGatewayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
    
}
