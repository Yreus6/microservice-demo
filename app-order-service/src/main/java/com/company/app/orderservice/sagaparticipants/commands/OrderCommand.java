package com.company.app.orderservice.sagaparticipants.commands;

import io.eventuate.tram.commands.common.Command;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class OrderCommand implements Command {
    
    private final Integer orderId;
}
