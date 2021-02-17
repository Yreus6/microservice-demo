package com.company.app.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.eventuate.common.json.mapper.JSonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
@Import({SwaggerConfiguration.class})
public class CommonConfiguration {
    
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = JSonMapper.objectMapper;
        mapper.registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new MoneyModule().withFastMoney());
        mapper.registerModule(new Hibernate5Module());
    
        return mapper;
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
