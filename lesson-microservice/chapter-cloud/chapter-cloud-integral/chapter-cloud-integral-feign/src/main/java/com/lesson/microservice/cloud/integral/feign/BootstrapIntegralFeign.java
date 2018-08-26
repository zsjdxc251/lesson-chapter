package com.lesson.microservice.cloud.integral.feign;


import com.lesson.microservice.boot.sample.microservice.cloud.integral.api.service.IOrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = {IOrderService.class})
public class BootstrapIntegralFeign {

    public static void main(String[] args){

        SpringApplication.run(BootstrapIntegralFeign.class,args);
    }
}
