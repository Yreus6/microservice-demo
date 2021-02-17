package com.company.app.orderservice.main.config;

import com.company.app.common.config.CommonConfiguration;
import com.company.app.orderservice.service.OrderCommandHandlers;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.company.app.orderservice"})
@EntityScan(basePackages = {"com.company.app.orderservice"})
@EnableJpaRepositories(basePackages = {"com.company.app.orderservice"})
@Import({
    CommonConfiguration.class,
    SagaOrchestratorConfiguration.class,
    OptimisticLockingDecoratorConfiguration.class,
    SagaParticipantConfiguration.class
})
public class OrderServiceConfiguration {

    @Bean
    public SagaCommandDispatcher sagaCommandDispatcher(
        OrderCommandHandlers target,
        SagaCommandDispatcherFactory factory
    ) {
        return factory.make("orderSagaCommandDispatcher", target.commandHandlersDefinition());
    }
}
