package com.company.app.orderservice.sagaparticipants.proxies;

import com.company.app.orderservice.api.sagaparticipants.channels.OrderServiceChannels;
import com.company.app.orderservice.sagaparticipants.commands.ApproveOrderCommand;
import com.company.app.orderservice.sagaparticipants.commands.RejectOrderCommand;
import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceProxy {
    
    public final CommandEndpoint<ApproveOrderCommand> approve = CommandEndpointBuilder
        .forCommand(ApproveOrderCommand.class)
        .withChannel(OrderServiceChannels.COMMAND_CHANNEL)
        .withReply(Success.class)
        .build();
    
    public final CommandEndpoint<RejectOrderCommand> reject = CommandEndpointBuilder
        .forCommand(RejectOrderCommand.class)
        .withChannel(OrderServiceChannels.COMMAND_CHANNEL)
        .withReply(Success.class)
        .build();
}
