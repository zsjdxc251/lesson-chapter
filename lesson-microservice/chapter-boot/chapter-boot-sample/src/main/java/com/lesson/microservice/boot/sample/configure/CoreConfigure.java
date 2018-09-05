package com.lesson.microservice.boot.sample.configure;

import com.lesson.microservice.boot.sample.interceptor.SimpleHandlerInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@Configuration
public class CoreConfigure implements WebMvcConfigurer, WebMvcRegistrations {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleHandlerInterceptor());
    }


    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {


        return null;
    }
}
