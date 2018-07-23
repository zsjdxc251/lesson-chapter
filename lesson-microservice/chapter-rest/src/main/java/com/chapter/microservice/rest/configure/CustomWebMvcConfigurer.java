package com.chapter.microservice.rest.configure;

import com.chapter.microservice.rest.converter.CustomHttpMessageConverter;
import com.chapter.microservice.rest.interceptor.CustomHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer ,ErrorPageRegistrar{

    private static final Logger log = LoggerFactory.getLogger(CustomWebMvcConfigurer.class);

    /**
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(new CustomHttpMessageConverter());
        log.info("configureMessageConverters converters size:{}",converters.size());

    }


    /**
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("extendMessageConverters converters size:{}",converters.size());
    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));

    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        registry.addInterceptor(new CustomHandlerInterceptor());



    }
}
