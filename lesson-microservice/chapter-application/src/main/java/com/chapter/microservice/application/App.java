package com.chapter.microservice.application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengshijun
 * @version created on 2018/7/20.
 */
@Configuration
public class App {

    public static void main(String[] args){

        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext();

        applicationContext.register(App.class);

        applicationContext.refresh();

       App app =  applicationContext.getBean(App.class);

       System.out.println(app);

    }
}
