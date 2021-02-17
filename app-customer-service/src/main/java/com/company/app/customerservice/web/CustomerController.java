package com.company.app.customerservice.web;

import com.company.app.customerservice.api.web.GetCustomerResponse;
import com.company.app.customerservice.domain.dto.CreateCustomerDto;
import com.company.app.customerservice.domain.entities.Customer;
import com.company.app.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
    path = "/api/v1/customers",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public GetCustomerResponse create(@Valid CreateCustomerDto createCustomerDto) {
        Customer customer = customerService.createCustomer(createCustomerDto);
        
        return makeGetCustomerResponse(customer);
    }
    
    @GetMapping(path = "/{id}")
    public GetCustomerResponse getOne(@PathVariable("id") Integer id) {
        Customer customer = customerService.getCustomerById(id);
        
        return makeGetCustomerResponse(customer);
    }
    
    private GetCustomerResponse makeGetCustomerResponse(Customer customer) {
        return new GetCustomerResponse(
            customer.getId(),
            customer.getName(),
            customer.getCreditLimit()
        );
    }
}
