package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FundooExceptionHandler {

    @ExceptionHandler(FundooException.class)
    private ResponseEntity handleException(FundooException userException){
        return new ResponseEntity(userException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
