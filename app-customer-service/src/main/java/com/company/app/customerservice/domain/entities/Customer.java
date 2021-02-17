package com.company.app.customerservice.domain.entities;

import com.company.app.common.converter.MonetaryAmountConverter;
import com.company.app.customerservice.domain.exceptions.CustomerCreditLimitExceededException;
import lombok.*;
import org.javamoney.moneta.FastMoney;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.util.Map;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@Access(AccessType.FIELD)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String name;
    
    @Column(name = "credit_limit")
    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount creditLimit;
    
    @ElementCollection
    @CollectionTable(name = "customer_credit_reservations")
    @Convert(converter = MonetaryAmountConverter.class, attributeName = "value")
    @Column(name = "credit_reservations")
    @MapKeyJoinColumn(name = "customer_id")
    private Map<Integer, MonetaryAmount> creditReservations;
    
    @Version
    @Column(name = "optlock")
    private Long version;
    
    public MonetaryAmount availableCredit() {
        return creditLimit.subtract(
            creditReservations.values()
                .stream()
                .reduce(FastMoney.of(0, "USD"), MonetaryAmount::add)
        );
    }
    
    public void reserveCredit(Integer orderId, MonetaryAmount totalPrice) {
        if (availableCredit().isGreaterThanOrEqualTo(totalPrice)) {
            creditReservations.put(orderId, totalPrice);
        } else {
            throw new CustomerCreditLimitExceededException();
        }
    }
}
