package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.domain.exception.CustomerAlreadyExistsException;
import com.spring.mscustomer.kafkaproducer.NewCustomerKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateCustomerUseCase {

    private final MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;
    private final NewCustomerKafkaProducer newCustomerKafkaProducer;

    public void execute(Customer customer){

        msCustomerDataBaseDataProvider
                .findByDocument(customer.getDocument())
                .ifPresent(customerFromDB ->  { throw new CustomerAlreadyExistsException("Customer already exists");});

        int generatedId = msCustomerDataBaseDataProvider.save(customer);

        customer.setId((long) generatedId);
        log.info("c=CreateCustomerUseCase, m=execute, customerAfterSave = {}", customer);

       newCustomerKafkaProducer.sendMessage(customer);
    }
}
