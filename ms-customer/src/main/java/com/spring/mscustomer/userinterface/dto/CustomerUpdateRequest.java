package com.spring.mscustomer.userinterface.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerUpdateRequest {

    private String name;
    private String document;
    private String email;
    private String phone;
}
