package com.lesson.distributed.rabbit.sample.direct;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;


/**
 *
 *
 *
 *
 */
public class RabbitDirectProvider implements SampleHandler {

    private final String exchangeName;

    public RabbitDirectProvider(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    @Override
    public void execute(Channel channel) throws Exception {

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,Boolean.TRUE);

        String routingKey = "444.warn";
        String message = exchangeName+"/发送消息";

        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());


        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");


    }

    public static void main(String[] args){

        RabbitApplication.execute(new RabbitDirectProvider("logs.error"));

    }
}
