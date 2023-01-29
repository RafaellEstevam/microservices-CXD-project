package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.userinterface.exception.CustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;

    public void execute(Customer customer){

        msCustomerDataBaseDataProvider
                .findByDocument(customer.getDocument())
                .ifPresent(customerFromDB -> new CustomerAlreadyExistsException("Customer already exists"));

        msCustomerDataBaseDataProvider.save(customer);
    }
}
