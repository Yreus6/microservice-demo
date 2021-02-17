package com.company.app.orderservice.domain.entities;

import com.company.app.orderservice.api.common.OrderState;
import com.company.app.orderservice.api.common.RejectionReason;
import com.company.app.orderservice.common.OrderDetails;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Embedded
    private OrderDetails orderDetails;
    
    @Column
    @Enumerated(EnumType.STRING)
    private OrderState state;
    
    @Column(name = "rejection_reason")
    @Enumerated(EnumType.STRING)
    private RejectionReason rejectionReason;
    
    @Version
    @Column(name = "optlock")
    private Long version;
    
    public Order(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
        this.state = OrderState.PENDING;
    }
}
