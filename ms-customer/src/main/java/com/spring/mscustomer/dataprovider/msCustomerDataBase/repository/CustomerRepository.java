package com.spring.mscustomer.dataprovider.msCustomerDataBase.repository;

import com.spring.mscustomer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(Long id);
    Optional<Customer> findByDocument(String document);
    List<Customer> findAll();
    int save(Customer customer);
    int update(Customer customer);
    int delete(Long id);
}
