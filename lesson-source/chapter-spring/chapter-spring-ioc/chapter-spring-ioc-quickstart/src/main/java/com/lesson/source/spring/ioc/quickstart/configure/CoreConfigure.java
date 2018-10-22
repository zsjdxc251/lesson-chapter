package com.lesson.source.spring.ioc.quickstart.configure;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author zhengshijun
 * @version created on 2018/10/19.
 */
@Configuration
public class CoreConfigure {


    @PostConstruct
    public void init(){

        System.out.println("初始化");

    }


    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor(){

        return new CustomBeanFactoryPostProcessor();
    }



}
