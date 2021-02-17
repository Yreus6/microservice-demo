package com.company.app.orderservice.main;

import com.company.app.orderservice.main.config.OrderServiceConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    OrderServiceConfiguration.class,
    TramMessageProducerJdbcConfiguration.class,
    EventuateTramKafkaMessageConsumerConfiguration.class
})
public class OrderServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    
}
