package com.spring.mscustomer.userinterface.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "document cannot be empty")
    private String document;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "phone cannot be empty")
    private String phone;

}
