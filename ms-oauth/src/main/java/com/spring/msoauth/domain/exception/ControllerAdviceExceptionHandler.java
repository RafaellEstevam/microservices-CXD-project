package com.spring.msoauth.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleUserNotFoundException(UserNotFoundException exception){
        ErrorResponse errorResponse = getErrorResponse(exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    private static ErrorResponse getErrorResponse(RuntimeException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
        return errorResponse;
    }
}
