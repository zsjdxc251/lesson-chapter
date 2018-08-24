package com.lesson.microservice.cloud.sleuth.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@SpringBootApplication
@EnableEurekaServer
public class BootstrapSleuthEureka {
    public static void main(String[] args){
        SpringApplication.run(BootstrapSleuthEureka.class,args);

    }
}
