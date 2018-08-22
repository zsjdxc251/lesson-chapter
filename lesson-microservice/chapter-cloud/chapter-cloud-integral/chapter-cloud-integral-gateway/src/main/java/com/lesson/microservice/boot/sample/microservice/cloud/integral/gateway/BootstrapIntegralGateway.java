package com.lesson.microservice.boot.sample.microservice.cloud.integral.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BootstrapIntegralGateway {
    public static void main(String[] args){
        SpringApplication.run(BootstrapIntegralGateway.class,args);
    }
}
