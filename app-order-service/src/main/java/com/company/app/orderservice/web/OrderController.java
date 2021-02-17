package com.company.app.orderservice.web;

import com.company.app.orderservice.api.web.GetOrderResponse;
import com.company.app.orderservice.domain.dto.CreateOrderDto;
import com.company.app.orderservice.domain.entities.Order;
import com.company.app.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
    path = "/api/v1/orders",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public GetOrderResponse create(@Valid CreateOrderDto createOrderDto) {
        return makeGetOrderResponse(orderService.createOrder(createOrderDto));
    }
    
    @GetMapping(path = "/{id}")
    public GetOrderResponse getOne(@PathVariable("id") Integer id) {
        return makeGetOrderResponse(orderService.getOrderById(id));
    }
    
    @GetMapping(path = "/customers/{customerId}")
    public List<GetOrderResponse> getAllByCustomerId(@PathVariable("customerId") Integer customerId) {
        return orderService.getOrdersByCustomerId(customerId)
            .stream()
            .map(this::makeGetOrderResponse)
            .collect(Collectors.toList());
    }
    
    private GetOrderResponse makeGetOrderResponse(Order order) {
        return new GetOrderResponse(
            order.getId(),
            order.getOrderDetails().getCustomerId(),
            order.getOrderDetails().getTotalPrice(),
            order.getState(),
            order.getRejectionReason()
        );
    }
}
