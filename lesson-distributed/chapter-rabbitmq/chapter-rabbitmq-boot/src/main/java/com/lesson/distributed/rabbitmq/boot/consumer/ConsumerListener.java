package com.lesson.distributed.rabbitmq.boot.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhengshijun
 * @version created on 2018/10/29.
 */
@Component
@RabbitListener(queues = "hello12")
public class ConsumerListener {

    @RabbitHandler
    public void process(Channel channel, Object message){

        if (message instanceof Message){

            System.out.println("匹配");
        }
        System.out.println(message.getClass());
    }
}
