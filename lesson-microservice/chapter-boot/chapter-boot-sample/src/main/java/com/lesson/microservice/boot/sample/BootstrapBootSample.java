package com.lesson.microservice.boot.sample;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootstrapBootSample
{
    public static void main( String[] args ){

        SpringApplication.run(BootstrapBootSample.class,args);

        String str = "name:";
    }
}
