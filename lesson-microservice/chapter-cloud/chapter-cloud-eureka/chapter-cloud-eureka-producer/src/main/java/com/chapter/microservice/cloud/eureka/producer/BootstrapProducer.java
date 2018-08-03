package com.chapter.microservice.cloud.eureka.producer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BootstrapProducer
{
    public static void main( String[] args )
    {


        SpringApplication.run(BootstrapProducer.class,args);

    }
}
