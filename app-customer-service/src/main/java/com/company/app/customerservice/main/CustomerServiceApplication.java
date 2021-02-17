package com.company.app.customerservice.main;

import com.company.app.customerservice.main.config.CustomerServiceConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    CustomerServiceConfiguration.class,
    TramMessageProducerJdbcConfiguration.class,
    EventuateTramKafkaMessageConsumerConfiguration.class
})
public class CustomerServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    
}
