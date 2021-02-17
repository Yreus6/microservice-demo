package com.company.app.customerservice.service;

import com.company.app.customerservice.api.sagaparticipants.channels.CustomerServiceChannels;
import com.company.app.customerservice.api.sagaparticipants.commands.ReserveCreditCommand;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerCreditLimitExceeded;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerCreditReserved;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerNotFound;
import com.company.app.customerservice.domain.exceptions.CustomerCreditLimitExceededException;
import com.company.app.customerservice.domain.exceptions.CustomerNotFoundException;
import io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCommandHandlers {
    
    private final CustomerService customerService;
    
    public CommandHandlers commandHandlersDefinition() {
        return SagaCommandHandlersBuilder
            .fromChannel(CustomerServiceChannels.COMMAND_CHANNEL)
            .onMessage(ReserveCreditCommand.class, this::reserveCredit)
            .build();
    }
    
    private Message reserveCredit(CommandMessage<ReserveCreditCommand> commandMessage) {
        ReserveCreditCommand command = commandMessage.getCommand();
        
        try {
            customerService.reserveCredit(command.getCustomerId(), command.getOrderId(), command.getTotalPrice());
            
            return CommandHandlerReplyBuilder.withSuccess(new CustomerCreditReserved());
        } catch (CustomerNotFoundException e) {
            return CommandHandlerReplyBuilder.withFailure(new CustomerNotFound());
        } catch (CustomerCreditLimitExceededException e) {
            return CommandHandlerReplyBuilder.withFailure(new CustomerCreditLimitExceeded());
        }
    }
}
