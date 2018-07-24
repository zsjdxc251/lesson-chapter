package com.chapter.microservice.rest.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Controller
@RequestMapping
public @interface CustomAnnotation {


    @AliasFor(annotation = RequestMapping.class,attribute = "value")
    String[] paths();


    @AliasFor(annotation = Controller.class,attribute = "value")
    String beanName() default "";
}
