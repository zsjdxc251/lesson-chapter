package com.lesson.distributed.rabbit.sample.queue;


import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author zhengshijun
 *
 *
 */
public class RabbitQueueConsumer implements SampleHandler {


    private static final Logger log = LoggerFactory.getLogger(RabbitQueueConsumer.class);


    private final String queueName;

    public RabbitQueueConsumer(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public void execute(Channel channel) throws Exception {
        log.info("listener:{}",queueName);


        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                log.info("message:{},queueName:{}",message,queueName);
            }
        };
        channel.basicConsume(queueName, true, consumer);

        Thread.currentThread().join();
    }
    public static void main(String[] args){
        String[] queueNames = {"queue_name_pink","queue_name_blue"};

        Stream.of(queueNames).parallel().forEach(queueName->RabbitApplication.execute(new RabbitQueueConsumer(queueName)));


    }

}
