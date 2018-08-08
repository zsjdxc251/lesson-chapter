package com.chapter.microservice.cloud.hystrix.turbine;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@SpringBootApplication
@EnableHystrixDashboard
public class App 
{
    public static void main( String[] args )
    {


        SpringApplication.run(App.class,args);
    }
}
