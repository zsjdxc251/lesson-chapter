package com.lesson.microservice.boot.sample.microservice.cloud.integral.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class BootstrapIntegralHystrix {

    public static void main(String[] args){

        SpringApplication.run(BootstrapIntegralHystrix.class,args);
    }
}
