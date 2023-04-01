package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.exception.CustomerNotFoundException;
import com.spring.mscustomer.domain.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerUseCaseTest {

    @Mock
    private MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;

    @InjectMocks
    private UpdateCustomerUseCase updateCustomerUseCase;

    public static final Long ID= 1L;
    public static final String NAME = "Johan Maples";
    public static final String DOCUMENT = "123.456.789-10";
    public static final String EMAIL = "johan.richardmaples@mock.com";
    public static final String PHONE = "98888-7777";
    public static final String NAMEUPDATE = "Johan Richard Maples";
    public static final String EMAILUPDATE = "johan.maples@mock.com";
    public static final String PHONEUPDATE = "99999-8888";

    public static final Customer CUSTOMERFROMDB= Customer.builder()
            .id(ID)
            .name(NAME)
            .document(DOCUMENT)
            .email(EMAIL)
            .phone(PHONE)
            .build();

    public static final Customer CUSTOMERFROMREQUEST= Customer.builder()
            .name(NAMEUPDATE)
            .email(EMAILUPDATE)
            .phone(PHONEUPDATE)
            .build();

    @Test
    public void shouldThrowAnExceptionAndNotSaveTheCustomerToDataBase(){

        when(msCustomerDataBaseDataProvider.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, () -> updateCustomerUseCase
                .execute(ArgumentMatchers.anyLong(), Customer.builder().build()));

        verify(msCustomerDataBaseDataProvider, Mockito.never()).update(ArgumentMatchers.any(Customer.class));
    }


    @Test
    public void shouldUpdateTheCustomerOnDataBaseCorrectly(){
        ArgumentCaptor<Customer>captor = ArgumentCaptor.forClass(Customer.class);

        when(msCustomerDataBaseDataProvider.findById(ID)).thenReturn(Optional.of(CUSTOMERFROMDB));
        updateCustomerUseCase.execute(ID, CUSTOMERFROMREQUEST);

        verify(msCustomerDataBaseDataProvider).update(captor.capture());

        Customer customerUpdate = captor.getValue();

        Assertions.assertEquals(ID, customerUpdate.getId());
        Assertions.assertEquals(DOCUMENT, customerUpdate.getDocument());
        Assertions.assertEquals(NAMEUPDATE, customerUpdate.getName());
        Assertions.assertEquals(EMAILUPDATE, customerUpdate.getEmail());
        Assertions.assertEquals(PHONEUPDATE, customerUpdate.getPhone());

    }

    @Test
    public void shouldKeepTheValuesFromDataBaseWhenCustomerRequestValuesAreNull(){
        ArgumentCaptor<Customer>captor = ArgumentCaptor.forClass(Customer.class);

        when(msCustomerDataBaseDataProvider.findById(ID)).thenReturn(Optional.of(CUSTOMERFROMDB));
        updateCustomerUseCase.execute(ID, Customer.builder().build());

        verify(msCustomerDataBaseDataProvider).update(CUSTOMERFROMDB);
    }


}