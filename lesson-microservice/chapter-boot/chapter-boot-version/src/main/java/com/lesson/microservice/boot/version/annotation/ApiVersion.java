package com.lesson.microservice.boot.version.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author zhengshijun
 *
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {


    @AliasFor(attribute = "version")
    int value() default 0;

    @AliasFor(attribute = "value")
    int version() default 0;
}
