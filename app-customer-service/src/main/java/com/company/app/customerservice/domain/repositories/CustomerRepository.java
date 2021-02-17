package com.company.app.customerservice.domain.repositories;

import com.company.app.customerservice.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
