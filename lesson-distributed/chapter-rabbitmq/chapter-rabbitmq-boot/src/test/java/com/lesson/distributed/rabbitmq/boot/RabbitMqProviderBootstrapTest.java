package com.lesson.distributed.rabbitmq.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitMqProviderBootstrap.class)
@RunWith(SpringRunner.class)
public class RabbitMqProviderBootstrapTest{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test1(){


        rabbitTemplate.send("","hello12",new Message("111".getBytes(),new MessageProperties()));
    }

}