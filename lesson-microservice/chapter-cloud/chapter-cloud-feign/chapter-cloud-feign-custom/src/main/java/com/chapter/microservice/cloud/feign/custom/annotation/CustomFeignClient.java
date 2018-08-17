package com.chapter.microservice.cloud.feign.custom.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomFeignClient {

    @AliasFor("name")
    String value() default "";


    @AliasFor("value")
    String name() default "";
}
