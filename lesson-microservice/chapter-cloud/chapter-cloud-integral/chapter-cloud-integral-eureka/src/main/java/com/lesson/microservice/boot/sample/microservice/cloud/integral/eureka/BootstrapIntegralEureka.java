package com.lesson.microservice.boot.sample.microservice.cloud.integral.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BootstrapIntegralEureka
{
    public static void main( String[] args )
    {

        SpringApplication.run(BootstrapIntegralEureka.class,args);
    }
}