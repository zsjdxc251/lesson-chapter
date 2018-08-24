package com.lesson.microservice.cloud.sleuth.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BootstrapSleuthSample {
    public static void main(String[] args){
        SpringApplication.run(BootstrapSleuthSample.class,args);

    }
}
