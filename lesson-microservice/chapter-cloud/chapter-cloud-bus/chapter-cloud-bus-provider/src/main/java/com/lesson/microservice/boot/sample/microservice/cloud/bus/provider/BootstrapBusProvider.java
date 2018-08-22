package com.lesson.microservice.boot.sample.microservice.cloud.bus.provider;

import com.lesson.microservice.boot.sample.microservice.cloud.bus.api.MessageRemoteApplicationEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@RemoteApplicationEventScan(basePackageClasses = {MessageRemoteApplicationEvent.class})
public class BootstrapBusProvider {

    public static void main(String[] args){
        SpringApplication.run(BootstrapBusProvider.class,args);

    }
}
