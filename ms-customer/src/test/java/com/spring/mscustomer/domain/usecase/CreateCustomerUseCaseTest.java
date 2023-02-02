package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.exception.CustomerAlreadyExistsException;
import com.spring.mscustomer.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CreateCustomerUseCaseTest {

    public static final long ID = 1L;
    public static final String NAME = "Johan Silvio Benedict";
    public static final String EMAIL = "johan.s@mock.com";
    public static final String PHONE = "(11)99898-5622";
    public static final String DOCUMENT = "123.456.789-82";
    public static final Optional<Customer> EMPTY_OPTIONAL  = Optional.empty();


    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;

    @MockBean
    private MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;

    private Customer customer;


    @BeforeEach
    void setUp(){
        startCustomer();
    }

    @Test
    void WhenCustomerIsNotPresentInDBThenSaveMethodMustBeCalled(){
        when(msCustomerDataBaseDataProvider.findByDocument(ArgumentMatchers.eq(DOCUMENT)))
                .thenReturn(EMPTY_OPTIONAL);

        createCustomerUseCase.execute(customer);
        Mockito.verify(msCustomerDataBaseDataProvider, Mockito.times(1)).save(ArgumentMatchers.eq(customer));
    }


    @Test
    void WhenCustomerIsPresentInDBThenShouldThrowAnException(){
        Mockito.when(msCustomerDataBaseDataProvider.findByDocument(DOCUMENT)).thenReturn(Optional.of(customer));
        assertThrows(CustomerAlreadyExistsException.class, () -> createCustomerUseCase.execute(customer));
        verify(msCustomerDataBaseDataProvider, Mockito.never()).save(ArgumentMatchers.any(Customer.class));
    }

    private void startCustomer() {

        customer = Customer.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .phone(PHONE)
                .document(DOCUMENT)
                .build();
    }
}