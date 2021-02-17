package com.company.app.orderservice.sagaparticipants.commands;

import com.company.app.orderservice.api.common.RejectionReason;
import io.eventuate.tram.commands.common.Command;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class RejectOrderCommand implements Command {
    
    private final Integer orderId;
    
    private final RejectionReason rejectionReason;
}
