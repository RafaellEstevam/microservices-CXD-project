package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomerUseCase {

    private final MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;

    public void execute(Long id){
        msCustomerDataBaseDataProvider.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));

        msCustomerDataBaseDataProvider.delete(id);
    }

}
