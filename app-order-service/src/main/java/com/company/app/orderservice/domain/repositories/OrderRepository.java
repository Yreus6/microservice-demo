package com.company.app.orderservice.domain.repositories;

import com.company.app.orderservice.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Integer> {

    List<Order> findAllByOrderDetails_CustomerId(Integer customerId);
}
