package com.company.app.orderservice.service;

import com.company.app.orderservice.api.common.OrderState;
import com.company.app.orderservice.api.common.RejectionReason;
import com.company.app.orderservice.domain.dto.CreateOrderDto;
import com.company.app.orderservice.domain.entities.Order;
import com.company.app.orderservice.domain.exceptions.OrderNotFoundException;
import com.company.app.orderservice.domain.repositories.OrderRepository;
import com.company.app.orderservice.domain.utils.OrderMapper;
import com.company.app.orderservice.sagas.createorder.CreateOrderSaga;
import com.company.app.orderservice.sagas.createorder.CreateOrderSagaData;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    
    private final SagaInstanceFactory sagaInstanceFactory;
    private final CreateOrderSaga createOrderSaga;
    
    @Transactional
    public Order createOrder(CreateOrderDto createOrderDto) {
        Order order = mapper.convertToEntity(createOrderDto);
        Order savedOrder = orderRepository.save(order);
        
        CreateOrderSagaData data = new CreateOrderSagaData(order.getOrderDetails());
        data.setOrderId(savedOrder.getId());
        sagaInstanceFactory.create(createOrderSaga, data);
        
        return savedOrder;
    }
    
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);
    }
    
    public List<Order> getOrdersByCustomerId(Integer customerId) {
        return orderRepository.findAllByOrderDetails_CustomerId(customerId);
    }
    
    public void approveOrder(Integer orderId) {
        Order order = setOrderState(orderId, OrderState.APPROVED);
        orderRepository.save(order);
    }
    
    public void rejectOrder(Integer orderId, RejectionReason rejectionReason) {
        Order order = setOrderState(orderId, OrderState.REJECTED);
        order.setRejectionReason(rejectionReason);
        orderRepository.save(order);
    }
    
    private Order setOrderState(Integer orderId, OrderState state) {
        Order order = getOrderById(orderId);
        order.setState(state);
        
        return order;
    }
}
