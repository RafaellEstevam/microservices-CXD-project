package com.spring.mscustomer.domain.dataprovider;

import com.spring.mscustomer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface MsCustomerDataBaseDataProvider {

    Optional<Customer> findById(Long id);
    Optional<Customer> findByDocument(String document);
    List<Customer>findAll();
    void create(Customer customer);
    void update(Customer customer);
    void delete(Long id);
}
