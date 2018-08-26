package com.lesson.microservice.cloud.sleuth.feign;

import com.lesson.microservice.cloud.sleuth.api.service.UserInfoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {UserInfoService.class})
public class BootstrapSleuthFeign {


    public static void main(String[] args){

        SpringApplication.run(BootstrapSleuthFeign.class,args);

    }
}
