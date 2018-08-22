package com.lesson.microservice.boot.sample.microservice.cloud.bus.consumer;

import com.lesson.microservice.boot.sample.microservice.cloud.bus.api.MessageRemoteApplicationEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;

@SpringBootApplication
@EnableDiscoveryClient
@RemoteApplicationEventScan(basePackageClasses = {MessageRemoteApplicationEvent.class})
public class BootstrapBusConsumer implements Lifecycle {

    public static void main(String[] args){

        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootstrapBusConsumer.class,args);
        applicationContext.addApplicationListener(new CustomApplicationListener());

        BusProperties busProperties = applicationContext.getBean(BusProperties.class);

        System.out.println(busProperties.getId());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
