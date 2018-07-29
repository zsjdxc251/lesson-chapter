package com.chapter.microservice.rest.view.exception;


import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Locale;

@RestControllerAdvice(annotations = {RestController})
public class RestExceptionHandler {


    @ExceptionHandler(Throwable.class)
    public String handler(WebExchangeBindException exchangeBindException, ServerWebExchange serverWebExchange){

        Locale locale = serverWebExchange.getLocaleContext().getLocale();

        BindingResult bindingResult = exchangeBindException.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        FieldError fieldError;


        return StringUtils.EMPTY;

    }
}
