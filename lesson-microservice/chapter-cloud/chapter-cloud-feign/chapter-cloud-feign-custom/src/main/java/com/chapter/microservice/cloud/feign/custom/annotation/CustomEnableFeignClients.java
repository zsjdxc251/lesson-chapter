package com.chapter.microservice.cloud.feign.custom.annotation;

import com.chapter.microservice.cloud.feign.custom.CustomFeignClientsRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CustomFeignClientsRegistrar.class)
public @interface CustomEnableFeignClients {

    Class<?>[] clients() default {};

}
