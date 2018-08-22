package com.lesson.microservice.boot.sample.microservice.cloud.bus.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BootstrapBusEureka {

    public static void main(String[] args){

        SpringApplication.run(BootstrapBusEureka.class,args);
    }
}
