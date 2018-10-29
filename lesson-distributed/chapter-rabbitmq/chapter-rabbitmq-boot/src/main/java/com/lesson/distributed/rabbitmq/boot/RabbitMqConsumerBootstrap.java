package com.lesson.distributed.rabbitmq.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
@SpringBootApplication(scanBasePackages = "com.lesson.distributed.rabbitmq.boot.consumer")
public class RabbitMqConsumerBootstrap {

    public static void main(String[] args){

        SpringApplication.run(RabbitMqConsumerBootstrap.class,args);
    }
}
