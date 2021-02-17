package com.company.app.orderservice.sagas.createorder;

import com.company.app.customerservice.api.sagaparticipants.replies.CustomerCreditLimitExceeded;
import com.company.app.customerservice.api.sagaparticipants.replies.CustomerNotFound;
import com.company.app.orderservice.sagaparticipants.proxies.CustomerServiceProxy;
import com.company.app.orderservice.sagaparticipants.proxies.OrderServiceProxy;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaData> {
    
    private SagaDefinition<CreateOrderSagaData> sagaDefinition;
    
    public CreateOrderSaga(
        OrderServiceProxy orderServiceProxy,
        CustomerServiceProxy customerServiceProxy
    ) {
        // @formatter:off
        sagaDefinition =
            step()
                .withCompensation(orderServiceProxy.reject, CreateOrderSagaData::makeRejectOrderCommand)
            .step()
                .invokeParticipant(customerServiceProxy.reserveCredit, CreateOrderSagaData::makeReserveCreditCommand)
                .onReply(CustomerNotFound.class, CreateOrderSagaData::handleCustomerNotFound)
                .onReply(CustomerCreditLimitExceeded.class, CreateOrderSagaData::handleCustomerCreditLimitExceeded)
            .step()
                .invokeParticipant(orderServiceProxy.approve, CreateOrderSagaData::makeApproveOrderCommand)
            .build();
        // @formatter:on
    }
    
    @Override
    public SagaDefinition<CreateOrderSagaData> getSagaDefinition() {
        return sagaDefinition;
    }
}
