package com.lesson.microservice.boot.sample.configure;

import com.lesson.microservice.boot.sample.interceptor.SimpleHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@Configuration
public class CoreConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleHandlerInterceptor());
    }
}
