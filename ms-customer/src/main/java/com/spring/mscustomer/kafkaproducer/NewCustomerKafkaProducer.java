package com.spring.mscustomer.kafkaproducer;

import com.spring.mscustomer.CustomerAvro;
import com.spring.mscustomer.domain.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NewCustomerKafkaProducer {

    @Value("${topic.name}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, CustomerAvro> kafkaTemplate;

    public void sendMessage(Customer customer){

        CustomerAvro customerAvro = CustomerAvro.newBuilder()
                .setId(customer.getId().intValue())
                .setName(customer.getName())
                .setDocument(customer.getDocument())
                .setEmail(customer.getEmail())
                .setPhone(customer.getPhone())
                .build();

        log.info("c=NewCustomerKafkaProducer, m=sendMessage, customerAvro={}", customerAvro);
        kafkaTemplate.send(topic, customerAvro);
    }
}
