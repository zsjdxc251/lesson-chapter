package com.chapter.microservice.cloud.hystrix.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableCircuitBreaker
public class BootstrapClient
{
    public static void main( String[] args )
    {
        SpringApplication.run(BootstrapClient.class,args);
    }
}
