package com.company.app.orderservice.sagaparticipants.proxies;

import com.company.app.customerservice.api.sagaparticipants.channels.CustomerServiceChannels;
import com.company.app.customerservice.api.sagaparticipants.commands.ReserveCreditCommand;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerCreditLimitExceeded;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerNotFound;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceProxy {

    public final CommandEndpoint<ReserveCreditCommand> reserveCredit = CommandEndpointBuilder
        .forCommand(ReserveCreditCommand.class)
        .withChannel(CustomerServiceChannels.COMMAND_CHANNEL)
        .withReply(CustomerNotFound.class)
        .withReply(CustomerCreditLimitExceeded.class)
        .build();
}
