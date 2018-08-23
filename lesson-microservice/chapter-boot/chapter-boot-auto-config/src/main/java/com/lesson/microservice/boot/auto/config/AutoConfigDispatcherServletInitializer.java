package com.lesson.microservice.boot.auto.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author zhengshijun
 * @version created on 2018/8/23.
 */
public class AutoConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("加载类");
        return new Class[]{CoreConfigure.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
