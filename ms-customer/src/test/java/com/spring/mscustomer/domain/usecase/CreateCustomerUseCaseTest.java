package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.exception.CustomerAlreadyExistsException;
import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.kafkaproducer.NewCustomerKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    public static final long ID = 1L;
    public static final String NAME = "Johan Silvio Benedict";
    public static final String EMAIL = "johan.s@mock.com";
    public static final String PHONE = "(11)99898-5622";
    public static final String DOCUMENT = "123.456.789-82";
    public static final Optional<Customer> EMPTY_OPTIONAL = Optional.empty();

    @Mock
    private MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;

    @Mock
    private NewCustomerKafkaProducer newCustomerKafkaProducer;

    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    private Customer customer = getCustomer();


    @Test
    void WhenCustomerIsNotPresentInDBThenSaveMethodMustBeCalled() {
        when(msCustomerDataBaseDataProvider.findByDocument(ArgumentMatchers.eq(DOCUMENT)))
                .thenReturn(EMPTY_OPTIONAL);

        createCustomerUseCase.execute(customer);
        verify(msCustomerDataBaseDataProvider, Mockito.times(1)).save(ArgumentMatchers.eq(customer));
        verify(newCustomerKafkaProducer, Mockito.times(1)).sendMessage(ArgumentMatchers.eq(customer));
    }


    @Test
    void WhenCustomerIsPresentInDBThenShouldThrowAnException() {
        when(msCustomerDataBaseDataProvider.findByDocument(DOCUMENT)).thenReturn(Optional.of(customer));
        assertThrows(CustomerAlreadyExistsException.class, () -> createCustomerUseCase.execute(customer));
        verify(msCustomerDataBaseDataProvider, Mockito.never()).save(ArgumentMatchers.any(Customer.class));
        verify(newCustomerKafkaProducer, Mockito.never()).sendMessage(ArgumentMatchers.any(Customer.class));
    }

    private Customer getCustomer() {
        return Customer.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .phone(PHONE)
                .document(DOCUMENT)
                .build();
    }
}