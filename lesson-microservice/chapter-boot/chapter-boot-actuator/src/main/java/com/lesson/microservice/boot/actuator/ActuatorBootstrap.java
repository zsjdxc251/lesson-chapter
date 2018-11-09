package com.lesson.microservice.boot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhengshijun
 * @version created on 2018/10/18.
 */
@SpringBootApplication
public class ActuatorBootstrap {

    public static void main(String[] args){
        SpringApplication.run(ActuatorBootstrap.class,args);

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
