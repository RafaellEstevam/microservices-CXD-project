package com.spring.msuser.userinterface.dto;

import com.spring.msuser.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotNull(message = "roles are required")
    @Size(min = 1)
    private Set<Role> roles = new HashSet<>();
}
