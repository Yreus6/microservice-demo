package com.company.app.orderservice.service;

import com.company.app.orderservice.api.sagaparticipants.channels.OrderServiceChannels;
import com.company.app.orderservice.domain.exceptions.OrderNotFoundException;
import com.company.app.orderservice.sagaparticipants.commands.ApproveOrderCommand;
import com.company.app.orderservice.sagaparticipants.commands.RejectOrderCommand;
import io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandHandlersBuilder;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCommandHandlers {
    
    private final OrderService orderService;
    
    public CommandHandlers commandHandlersDefinition() {
        return CommandHandlersBuilder
            .fromChannel(OrderServiceChannels.COMMAND_CHANNEL)
            .onMessage(ApproveOrderCommand.class, this::approve)
            .onMessage(RejectOrderCommand.class, this::reject)
            .build();
    }
    
    private Message approve(CommandMessage<ApproveOrderCommand> commandMessage) {
        ApproveOrderCommand command = commandMessage.getCommand();
        
        try {
            orderService.approveOrder(command.getOrderId());
            
            return CommandHandlerReplyBuilder.withSuccess();
        } catch (OrderNotFoundException e) {
            return CommandHandlerReplyBuilder.withFailure();
        }
    }
    
    private Message reject(CommandMessage<RejectOrderCommand> commandMessage) {
        RejectOrderCommand command = commandMessage.getCommand();
        
        try {
            orderService.rejectOrder(command.getOrderId(), command.getRejectionReason());
            
            return CommandHandlerReplyBuilder.withSuccess();
        } catch (OrderNotFoundException e) {
            return CommandHandlerReplyBuilder.withFailure();
        }
    }
}
