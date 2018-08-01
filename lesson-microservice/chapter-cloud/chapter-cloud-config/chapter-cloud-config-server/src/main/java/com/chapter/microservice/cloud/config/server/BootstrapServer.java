package com.chapter.microservice.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class BootstrapServer
{
    public static void main( String[] args ){

        SpringApplication.run(BootstrapServer.class,args);
    }
}
