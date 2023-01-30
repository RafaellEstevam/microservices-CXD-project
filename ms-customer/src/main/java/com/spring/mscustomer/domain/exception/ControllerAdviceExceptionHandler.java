package com.spring.mscustomer.domain.exception;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>>handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ErrorResponse>errorResponseList = fieldErrors
                .stream()
                .map(fieldError -> new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorResponseList);

    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception){
        ErrorResponse errorResponse = getErrorResponse(exception);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleCustomerNotFoundException(CustomerNotFoundException exception){
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
