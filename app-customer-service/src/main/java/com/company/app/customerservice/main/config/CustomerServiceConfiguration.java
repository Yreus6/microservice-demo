package com.company.app.customerservice.main.config;

import com.company.app.common.config.CommonConfiguration;
import com.company.app.customerservice.service.CustomerCommandHandlers;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.company.app.customerservice"})
@EntityScan(basePackages = {"com.company.app.customerservice"})
@EnableJpaRepositories(basePackages = "com.company.app.customerservice")
@Import({
    CommonConfiguration.class,
    SagaParticipantConfiguration.class,
    OptimisticLockingDecoratorConfiguration.class
})
public class CustomerServiceConfiguration {
    
    @Bean
    public CommandDispatcher customerCommandDispatcher(
        CustomerCommandHandlers target,
        SagaCommandDispatcherFactory factory
    ) {
        return factory.make("customerCommandDispatcher", target.commandHandlersDefinition());
    }
}
