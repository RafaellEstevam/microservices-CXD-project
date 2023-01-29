package com.spring.mscustomer.domain.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Customer {

    private Long id;
    private String name;
    private String document;
    private String email;
    private String phone;

}
