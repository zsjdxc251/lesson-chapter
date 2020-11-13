package com.chapter.microservice.cloud.eureka.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BootstrapServer
{
    public static void main( String[] args )
    {


        SpringApplication.run(BootstrapServer.class,args);
       // new SpringApplicationBuilder(BootstrapServer.class).run(args);

    }
}
