package com.lesson.distributed.rabbitmq.spring.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
@Component
@RabbitListener(queues = {"queue_name_info"},containerFactory="simpleRabbitListenerContainerFactory")
public class ConsumerListener implements MessageListener {

    @RabbitHandler
    public void process(String message) {

        System.out.println(message);
    }

    @Override
    public void onMessage(Message message) {

        System.out.println(new String(message.getBody()));

    }
}
