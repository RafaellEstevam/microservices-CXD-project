package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.domain.exception.CustomerNotFoundException;
import com.spring.mscustomer.util.DocumentFormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveCustomerUseCase {

    private final MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;
    private final DocumentFormatService documentFormatService;
    public Customer retrieveById(Long id){
        return msCustomerDataBaseDataProvider.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
    }

    public Customer retrieveByDocument(String document){
        return msCustomerDataBaseDataProvider.findByDocument(documentFormatService.execute(document))
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
    }

    public List<Customer>retrieveAll(){
        return msCustomerDataBaseDataProvider.findAll();
    }

}
