package com.spring.mscustomer.userinterface.controller;

import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.domain.usecase.CreateCustomerUseCase;
import com.spring.mscustomer.domain.usecase.DeleteCustomerUseCase;
import com.spring.mscustomer.domain.usecase.RetrieveCustomerUseCase;
import com.spring.mscustomer.domain.usecase.UpdateCustomerUseCase;
import com.spring.mscustomer.userinterface.dto.CustomerRequest;
import com.spring.mscustomer.userinterface.dto.CustomerResponse;
import com.spring.mscustomer.userinterface.dto.CustomerUpdateRequest;
import com.spring.mscustomer.userinterface.translator.CustomerResponseTranslator;
import com.spring.mscustomer.userinterface.translator.CustomerTranslator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerWebRestController {

    private final CustomerTranslator customerTranslator;
    private final CustomerResponseTranslator customerResponseTranslator;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final RetrieveCustomerUseCase retrieveCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @PostMapping
    public ResponseEntity<Void>create(@Valid @RequestBody CustomerRequest customerRequest){
        Customer customer = customerTranslator.execute(customerRequest);
        createCustomerUseCase.execute(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse>retrieveById(@PathVariable Long id){
        Customer customer = retrieveCustomerUseCase.retrieveById(id);
        return ResponseEntity.ok(customerResponseTranslator.execute(customer));
    }

    @GetMapping
    public ResponseEntity<CustomerResponse>retrieveByDocument(@RequestParam String document){
        Customer customer = retrieveCustomerUseCase.retrieveByDocument(document);
        return ResponseEntity.ok(customerResponseTranslator.execute(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>>retrieveAll(){
        List<Customer> customers = retrieveCustomerUseCase.retrieveAll();
        return ResponseEntity.ok(customerResponseTranslator.execute(customers));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void>pathUpdate(@PathVariable Long id, @RequestBody CustomerUpdateRequest customerUpdateRequest){
        Customer customer = customerTranslator.execute(customerUpdateRequest);
        updateCustomerUseCase.execute(id, customer);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        deleteCustomerUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }


}
