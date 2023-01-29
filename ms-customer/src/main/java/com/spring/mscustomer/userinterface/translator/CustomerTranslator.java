package com.spring.mscustomer.userinterface.translator;

import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.userinterface.dto.CustomerRequest;
import com.spring.mscustomer.userinterface.dto.CustomerUpdateRequest;
import com.spring.mscustomer.util.DocumentFormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerTranslator {

    private final DocumentFormatService documentFormatService;

    public Customer execute(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .document(documentFormatService.execute(customerRequest.getDocument()))
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .build();
    }

    public Customer execute(CustomerUpdateRequest customerUpdateRequest){
        return Customer.builder()
                .name(customerUpdateRequest.getName())
                .document(documentFormatService.execute(customerUpdateRequest.getDocument()))
                .email(customerUpdateRequest.getEmail())
                .phone(customerUpdateRequest.getPhone())
                .build();
    }
}
