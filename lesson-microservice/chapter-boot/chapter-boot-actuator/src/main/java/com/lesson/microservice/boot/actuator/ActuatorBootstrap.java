package com.lesson.microservice.boot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhengshijun
 * @version created on 2018/10/18.
 */
@SpringBootApplication
public class ActuatorBootstrap {

    public static void main(String[] args){
        SpringApplication.run(ActuatorBootstrap.class,args);

    }
}
