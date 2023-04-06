package com.spring.mscustomer.kafkaproducer;

import com.spring.mscustomer.CustomerAvro;
import com.spring.mscustomer.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NewCustomerKafkaProducerTest {

    public static final long ID = 1L;
    public static final String DOCUMENT = "123.456.789-00";
    public static final String NAME = "Vicente Marcelo Nogueira";
    public static final String PHONE = "(31) 98261-9279";
    public static final String EMAIL = "vicente.nogueira@mock.com";

    @Value("${topic.name}")
    private String TOPIC;

    @Mock
    private KafkaTemplate<String, CustomerAvro> kafkaTemplate;

    @InjectMocks
    private NewCustomerKafkaProducer newCustomerKafkaProducer;


    @Test
    public void shouldCallKafkaSendMessageCorrectly() {

        ArgumentCaptor<CustomerAvro>captor = ArgumentCaptor.forClass(CustomerAvro.class);

        Customer customer = Customer.builder()
                .id(ID)
                .document(DOCUMENT)
                .name(NAME)
                .phone(PHONE)
                .email(EMAIL)
                .build();

        newCustomerKafkaProducer.sendMessage(customer);

        Mockito.verify(kafkaTemplate).send(ArgumentMatchers.eq(TOPIC), captor.capture());

        CustomerAvro captorValue = captor.getValue();

        assertEquals(ID, captorValue.getId());
        assertEquals(DOCUMENT, captorValue.getDocument());
        assertEquals(NAME, captorValue.getName());
        assertEquals(PHONE, captorValue.getPhone());
        assertEquals(EMAIL, captorValue.getEmail());

    }
}