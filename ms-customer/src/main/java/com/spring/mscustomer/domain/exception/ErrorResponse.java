package com.spring.mscustomer.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    @JsonInclude(Include.NON_NULL)
    private String field;
    private String message;
}
