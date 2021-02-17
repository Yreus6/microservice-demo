package com.company.app.apigateway.order;

import com.company.app.apigateway.proxies.CustomerServiceProxy;
import com.company.app.apigateway.proxies.OrderServiceProxy;
import com.company.app.apigateway.web.GetCustomerHistoryResponse;
import com.company.app.customerservice.api.web.GetCustomerResponse;
import com.company.app.orderservice.api.web.GetOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderHistoryHandlers {

    private final OrderServiceProxy orderServiceProxy;
    private final CustomerServiceProxy customerServiceProxy;
    
    public Mono<ServerResponse> getOrderHistory(ServerRequest request) {
        String customerId = request.pathVariable("customerId");
        
        Mono<GetCustomerResponse> customer = customerServiceProxy.getCustomerById(customerId);
        Mono<List<GetOrderResponse>> orders = orderServiceProxy.getOrdersByCustomerId(customerId);
        
        return Mono.zip(customer, orders)
            .map(tuple2 -> {
                return new GetCustomerHistoryResponse(
                    tuple2.getT1().getId(),
                    tuple2.getT1().getName(),
                    tuple2.getT1().getCreditLimit(),
                    tuple2.getT2()
                );
            })
            .flatMap(getCustomerHistoryResponse -> {
                return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(getCustomerHistoryResponse);
            });
    }
}
