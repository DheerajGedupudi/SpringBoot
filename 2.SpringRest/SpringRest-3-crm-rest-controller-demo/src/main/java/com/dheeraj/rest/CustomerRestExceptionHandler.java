package com.dheeraj.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<CustomerError> handleException(CustomerNotFoundException e)
    {
        CustomerError error = new CustomerError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<CustomerError> handleException(Exception e)
    {
        CustomerError error = new CustomerError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
