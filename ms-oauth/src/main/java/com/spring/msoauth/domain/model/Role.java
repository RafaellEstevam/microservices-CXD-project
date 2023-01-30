package com.spring.msoauth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Role {

    private Long id;
    private String roleName;
}
