package com.company.app.orderservice.sagaparticipants.commands;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ApproveOrderCommand extends OrderCommand {
    
    public ApproveOrderCommand(Integer orderId) {
        super(orderId);
    }
}
