package com.spring.mscustomer.dataprovider.msCustomerDataBase;

import com.spring.mscustomer.dataprovider.msCustomerDataBase.repository.JdbcCustomerRepository;
import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MsCustomerDataBaseDataProviderImpl implements MsCustomerDataBaseDataProvider {

    private final JdbcCustomerRepository jdbcCustomerRepository;

    @Override
    public Optional<Customer> findById(Long id) {
        return jdbcCustomerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByDocument(String document) {
        return jdbcCustomerRepository.findByDocument(document);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return jdbcCustomerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        jdbcCustomerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        jdbcCustomerRepository.update(customer);
    }

    @Override
    public void delete(Long id) {
        jdbcCustomerRepository.delete(id);
    }
}
