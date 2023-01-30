package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.exception.CustomerNotFoundException;
import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.util.DocumentFormatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RetrieveCustomerUseCaseTest {

    public static final String NAME = "Daniel Baião";
    public static final long ID = 1L;
    public static final String DOCUMENT = "12365498911";
    public static final String EMAIL = "daniel.baiao@gmail.com";
    public static final String PHONE = "(11)99856-6522";
    public static final Optional<Customer>EMPTY_OPTIONAL = Optional.empty();

    @MockBean
    private  MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;

    @Autowired
    private DocumentFormatService documentFormatService;

    @Autowired
    private RetrieveCustomerUseCase retrieveCustomerUseCase;


    private Optional<Customer> optionalCustomer;

    @BeforeEach
    void setUp() {
        startCustomer();
    }

    @Test
    void WhenCustomerByIdIsPresentThenReturnTheCustomer() {
        Mockito.when(msCustomerDataBaseDataProvider.findById(ArgumentMatchers.eq(1L)))
                .thenReturn(optionalCustomer);

        Customer customer = retrieveCustomerUseCase.retrieveById(1L);
        Assertions.assertEquals(NAME,customer.getName());

    }

    @Test
    void WhenCustomerByIdIsNotPresentThenShouldThrowAException() {
        Mockito.when(msCustomerDataBaseDataProvider.findById(ArgumentMatchers.eq(1L)))
                .thenReturn(EMPTY_OPTIONAL);

       Assertions.assertThrows(CustomerNotFoundException.class, () -> retrieveCustomerUseCase.retrieveById(1L));

    }


    private void startCustomer(){
        optionalCustomer = Optional.of(new Customer(ID, NAME, DOCUMENT, EMAIL, PHONE));
    }


}