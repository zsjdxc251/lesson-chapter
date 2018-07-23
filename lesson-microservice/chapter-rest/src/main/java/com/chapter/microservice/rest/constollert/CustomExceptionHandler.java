package com.chapter.microservice.rest.constollert;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(value = {NullPointerException.class})
    public Object handler(Object params){

        return params;
    }
}
