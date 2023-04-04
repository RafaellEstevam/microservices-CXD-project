package com.spring.mscustomer.userinterface.controller;

import com.spring.mscustomer.domain.model.Customer;
import com.spring.mscustomer.domain.usecase.CreateCustomerUseCase;
import com.spring.mscustomer.domain.usecase.DeleteCustomerUseCase;
import com.spring.mscustomer.domain.usecase.RetrieveCustomerUseCase;
import com.spring.mscustomer.domain.usecase.UpdateCustomerUseCase;
import com.spring.mscustomer.userinterface.translator.CustomerResponseTranslator;
import com.spring.mscustomer.userinterface.translator.CustomerTranslator;
import com.spring.mscustomer.util.DocumentFormatService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = {CustomerWebRestController.class, CustomerTranslator.class, DocumentFormatService.class, CustomerResponseTranslator.class})
class CustomerWebRestControllerTest {

    @MockBean
    private CreateCustomerUseCase createCustomerUseCase;

    @MockBean
    private RetrieveCustomerUseCase retrieveCustomerUseCase;

    @MockBean
    private UpdateCustomerUseCase updateCustomerUseCase;

    @MockBean
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @Autowired
    private MockMvc mockMvc;

    private static final String CUSTOMER_REQUEST = "{\n" +
            "    \"name\": \"Aline Giovanna Corte Real\",\n" +
            "    \"document\": \"617.079.854-80\",\n" +
            "    \"email\": \"aline_cortereal@verdana.com.br\",\n" +
            "    \"phone\": \"(61)99728-8363\"\n" +
            "}";
    private static final String FORMATTED_DOCUMENT = "61707985480";

    public static final long ID = 1L;
    public static final String NAME = "Aline Giovanna Corte Real";
    public static final String EMAIL = "aline_cortereal@verdana.com.br";
    public static final String PHONE = "(61)99728-8363";
    public static final String DOCUMENT = "617.079.854-80";

    @Test
    public void shouldCallUseCaseCorrectly() throws Exception {

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(CUSTOMER_REQUEST);


        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(createCustomerUseCase).execute(captor.capture());

        Customer customer = captor.getValue();

        assertNotNull(customer.getName());
        assertNotNull(customer.getEmail());
        assertNotNull(customer.getPhone());
        assertEquals(FORMATTED_DOCUMENT, customer.getDocument());
    }


    @Test
    public void shouldRetrieveCustomerById() throws Exception {

        Customer customer = Customer.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .phone(PHONE)
                .document(DOCUMENT)
                .build();

        Mockito.when(retrieveCustomerUseCase.retrieveById(ArgumentMatchers.anyLong())).thenReturn(customer);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{\n" +
                                "    \"id\": 1,\n" +
                                "    \"name\": \"Aline Giovanna Corte Real\",\n" +
                                "    \"document\": \"617.079.854-80\",\n" +
                                "    \"email\": \"aline_cortereal@verdana.com.br\",\n" +
                                "    \"phone\": \"(61)99728-8363\"\n" +
                                "}"));

    }


    @Test
    public void shouldCallUseCaseCorrectlyInPatchUpdate() throws Exception {

        Customer customer = Customer.builder()
                .name(NAME)
                .email(EMAIL)
                .phone(PHONE)
                .document(FORMATTED_DOCUMENT)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .patch("/customer/1")
                .content(CUSTOMER_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(updateCustomerUseCase).execute(ArgumentMatchers.eq(1L),ArgumentMatchers.eq(customer));
    }
}





















