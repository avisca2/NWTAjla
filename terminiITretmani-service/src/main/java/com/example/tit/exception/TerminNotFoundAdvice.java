package com.example.tit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class TerminNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TerminNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String terminNotFoundHandler(TerminNotFoundException ex) {
        return ex.getMessage();
    }
    
}
