package com.company.app.apigateway.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.eventuate.common.json.mapper.JSonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
public class CommonConfiguration {
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = JSonMapper.objectMapper;
        objectMapper.registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new MoneyModule().withFastMoney());
        
        return objectMapper;
    }
}
