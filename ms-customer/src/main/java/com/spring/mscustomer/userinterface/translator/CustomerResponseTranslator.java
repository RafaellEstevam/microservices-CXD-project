package com.spring.mscustomer.userinterface.translator;

import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.userinterface.dto.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerResponseTranslator {
    public CustomerResponse execute(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .document(customer.getDocument())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    public List<CustomerResponse>execute(List<Customer> customers){
        return customers.stream()
                .map(customer -> execute(customer))
                .collect(Collectors.toList());
    }

}
