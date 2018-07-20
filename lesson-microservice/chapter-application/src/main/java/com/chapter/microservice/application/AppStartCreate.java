package com.chapter.microservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/7/20.
 */
@SpringBootApplication
public class AppStartCreate {

    public static void main(String[] args){



        application(args);



    }

    public static void simple(String[] args){
        SpringApplication.run(AppStartCreate.class,args);

    }

    public static void application(String[] args){
        SpringApplication springBootApplication = new SpringApplication(AppStartCreate.class);
        Properties properties = new Properties();
        properties.put("server.port",85);
        springBootApplication.setDefaultProperties(properties);

        springBootApplication.setWebApplicationType(WebApplicationType.REACTIVE);

        ConfigurableApplicationContext applicationContext = springBootApplication.run(args);

        System.err.println(applicationContext.getClass().getName());
    }

    public static void builder(String[] args){

        new SpringApplicationBuilder(AppStartCreate.class).properties("server.port=0").build()
        .run(args);

    }
}
