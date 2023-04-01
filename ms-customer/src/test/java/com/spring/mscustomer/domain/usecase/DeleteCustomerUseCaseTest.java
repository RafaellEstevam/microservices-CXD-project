package com.spring.mscustomer.domain.usecase;

import com.spring.mscustomer.domain.dataprovider.MsCustomerDataBaseDataProvider;
import com.spring.mscustomer.domain.exception.CustomerNotFoundException;
import com.spring.mscustomer.domain.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseTest {

    @Mock
    private MsCustomerDataBaseDataProvider msCustomerDataBaseDataProvider;

    @InjectMocks
    private DeleteCustomerUseCase deleteCustomerUseCase;


    @Test
    public void shouldDeleteTheCustomerWithNoProblem(){

        when(msCustomerDataBaseDataProvider.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(Customer.builder().build()));
        deleteCustomerUseCase.execute(ArgumentMatchers.anyLong());

        verify(msCustomerDataBaseDataProvider).delete(ArgumentMatchers.anyLong());
    }


    @Test
    public void shouldThrowAnExceptionBecauseCustomerWasNotFoundOnDataBase(){

        when(msCustomerDataBaseDataProvider.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, () -> deleteCustomerUseCase.execute(ArgumentMatchers.anyLong()));

        verify(msCustomerDataBaseDataProvider, Mockito.never()).delete(ArgumentMatchers.anyLong());
    }

}