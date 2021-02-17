package com.company.app.orderservice.sagas.createorder;

import com.company.app.customerservice.api.sagaparticipants.commands.ReserveCreditCommand;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerCreditLimitExceeded;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerNotFound;
import com.company.app.orderservice.api.common.RejectionReason;
import com.company.app.orderservice.common.OrderDetails;
import com.company.app.orderservice.sagaparticipants.commands.ApproveOrderCommand;
import com.company.app.orderservice.sagaparticipants.commands.RejectOrderCommand;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class CreateOrderSagaData {
    
    private final OrderDetails orderDetails;
    
    private Integer orderId;
    
    private RejectionReason rejectionReason;
    
    public RejectOrderCommand makeRejectOrderCommand() {
        return new RejectOrderCommand(orderId, rejectionReason);
    }
    
    public ReserveCreditCommand makeReserveCreditCommand() {
        assert orderDetails != null;
        return new ReserveCreditCommand(orderId, orderDetails.getTotalPrice(), orderDetails.getCustomerId());
    }
    
    public ApproveOrderCommand makeApproveOrderCommand() {
        return new ApproveOrderCommand(orderId);
    }
    
    public void handleCustomerNotFound(CustomerNotFound reply) {
        setRejectionReason(RejectionReason.UNKNOWN_CUSTOMER);
    }
    
    public void handleCustomerCreditLimitExceeded(CustomerCreditLimitExceeded reply) {
        setRejectionReason(RejectionReason.INSUFFICIENT_CREDIT);
    }
}
