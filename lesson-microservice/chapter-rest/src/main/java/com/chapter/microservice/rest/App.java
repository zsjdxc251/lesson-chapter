package com.chapter.microservice.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Hello world!
 *
 */


@SpringBootApplication
public class App
{
    
    private static final Logger log = LoggerFactory.getLogger(App.class);

    static RestTemplate restTemplate = new RestTemplate();


    public static void main( String[] args )
    {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class,args);

        String[] names = applicationContext.getBeanDefinitionNames();

        log.info("names:{}",names);
        


    }
}
