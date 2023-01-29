package com.spring.mscustomer.userinterface.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {

    private Long id;
    private String name;
    private String document;
    private String email;
    private String phone;
}
