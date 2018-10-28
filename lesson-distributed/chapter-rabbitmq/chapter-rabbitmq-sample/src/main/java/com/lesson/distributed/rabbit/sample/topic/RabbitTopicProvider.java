package com.lesson.distributed.rabbit.sample.topic;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;



public class RabbitTopicProvider implements SampleHandler {


    private final String exchangeName ;

    public RabbitTopicProvider(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    @Override
    public void execute(Channel channel) throws Exception {


        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true);

        String routingKey = "1.info";
        String message = exchangeName+"/发送消息";
        channel.basicPublish(exchangeName,routingKey,null,message.getBytes());





    }


    public static void main(String[] args){

        RabbitApplication.execute(new RabbitTopicProvider("logs.trace"));
    }
}
