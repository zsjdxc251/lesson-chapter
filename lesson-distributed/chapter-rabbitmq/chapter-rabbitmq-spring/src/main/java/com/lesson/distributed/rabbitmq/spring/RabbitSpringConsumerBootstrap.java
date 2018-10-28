package com.lesson.distributed.rabbitmq.spring;

import com.lesson.distributed.rabbitmq.spring.config.ConsumerConfigure;
import com.lesson.distributed.rabbitmq.spring.config.CoreConfigure;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
public class RabbitSpringConsumerBootstrap {


    public static void main(String[] args) throws Exception{


        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(CoreConfigure.class, ConsumerConfigure.class);

        Thread.currentThread().join();
    }
}
