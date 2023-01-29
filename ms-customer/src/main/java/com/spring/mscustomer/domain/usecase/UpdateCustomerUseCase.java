package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.userinterface.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;
    public void execute(Long id, Customer customerFromRequest){

        Customer customerFromDB = msCustomerDataBaseDataProvider.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));

        Customer customerUpdate = Customer.builder()
                .id(id)
                .name(isEmptyOrNull(customerFromRequest.getName()) ? customerFromDB.getName() : customerFromRequest.getName())
                .document(isEmptyOrNull(customerFromRequest.getDocument()) ? customerFromDB.getDocument() : customerFromRequest.getDocument())
                .email(isEmptyOrNull(customerFromRequest.getEmail()) ? customerFromDB.getEmail() : customerFromRequest.getEmail())
                .phone(isEmptyOrNull(customerFromRequest.getPhone()) ? customerFromDB.getPhone() : customerFromRequest.getPhone())
                .build();

        msCustomerDataBaseDataProvider.update(customerUpdate);
    }

    private Boolean isEmptyOrNull(String text){
        return text == null || text.isBlank() ;
    }

}
