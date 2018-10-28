package com.lesson.distributed.rabbitmq.spring;

import com.lesson.distributed.rabbitmq.spring.config.CoreConfigure;
import com.lesson.distributed.rabbitmq.spring.config.ProviderConfigure;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
public class RabbitSpringProviderBootstrap {


    public static void main(String[] args){


        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(CoreConfigure.class, ProviderConfigure.class);

        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

        rabbitTemplate.send("logs.error","*.info",new Message("demo".getBytes(),new MessageProperties()));
    }
}
