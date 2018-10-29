package com.lesson.distributed.rabbitmq.boot;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
@SpringBootApplication
public class RabbitMqProviderBootstrap {

    public static void main(String[] args){

        SpringApplication.run(RabbitMqProviderBootstrap.class,args);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){


        return new RabbitTemplate(connectionFactory);
    }


}
