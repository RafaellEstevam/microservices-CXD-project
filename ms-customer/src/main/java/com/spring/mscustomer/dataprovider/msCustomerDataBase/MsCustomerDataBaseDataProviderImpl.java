package com.spring.mscustomer.dataprovider.msCustomerDataBase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class MsCustomerDataBaseDataProviderImpl implements MsCustomerDataBaseDataProvider {
    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByDocument(String document) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Long id) {

    }
}
