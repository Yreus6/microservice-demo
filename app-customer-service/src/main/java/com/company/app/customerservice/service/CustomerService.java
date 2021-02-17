package com.company.app.customerservice.service;

import com.company.app.customerservice.domain.dto.CreateCustomerDto;
import com.company.app.customerservice.domain.entities.Customer;
import com.company.app.customerservice.domain.exceptions.CustomerCreditLimitExceededException;
import com.company.app.customerservice.domain.exceptions.CustomerNotFoundException;
import com.company.app.customerservice.domain.repositories.CustomerRepository;
import com.company.app.customerservice.domain.utils.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.money.MonetaryAmount;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    
    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        Customer customer = mapper.convertToEntity(createCustomerDto);
        
        return customerRepository.save(customer);
    }
    
    @Transactional
    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);
        customer.getCreditReservations().hashCode();
        
        return customer;
    }
    
    public void reserveCredit(Integer customerId, Integer orderId, MonetaryAmount totalPrice)
        throws CustomerCreditLimitExceededException {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(CustomerNotFoundException::new);
        customer.reserveCredit(orderId, totalPrice);
    }
}
