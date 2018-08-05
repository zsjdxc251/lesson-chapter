package com.chapter.microservice.cloud.zookeeper;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class BootstrapConsumer
{
    public static void main( String[] args )
    {
        SpringApplication.run(BootstrapConsumer.class,args);
    }
}
