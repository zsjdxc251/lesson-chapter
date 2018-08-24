package com.lesson.microservice.cloud.sleuth.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@SpringBootApplication
@EnableZipkinServer
public class BootstrapSleuthZipkin {
    public static void main(String[] args){
        SpringApplication.run(BootstrapSleuthZipkin.class,args);
    }
}
