package com.lesson.distributed.rabbit.sample.direct;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 *
 *
 *
 *
 */
public class RabbitDirectConsumer implements SampleHandler {

    private static final Logger log = LoggerFactory.getLogger(RabbitDirectConsumer.class);
    
    
    private final String exchangeName;
    
    

    public RabbitDirectConsumer(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    @Override
    public void execute(Channel channel) throws Exception {

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();
        log.info("queueName:{}",queueName);
    
        String routingKey = "demo.warn";
        if (StringUtils.isNotBlank(routingKey)){
            channel.queueBind(queueName,exchangeName,routingKey);

        }



        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };


        channel.basicConsume(queueName,true,consumer);


        Thread.currentThread().join();


    }

    public static void main(String[] args){

        RabbitApplication.execute(new RabbitDirectConsumer("logs.warn"));

    }
}
