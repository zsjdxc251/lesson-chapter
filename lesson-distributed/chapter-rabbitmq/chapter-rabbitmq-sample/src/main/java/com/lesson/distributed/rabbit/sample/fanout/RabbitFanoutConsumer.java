package com.lesson.distributed.rabbit.sample.fanout;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class RabbitFanoutConsumer implements SampleHandler {


    private final String exchangeName;


    public RabbitFanoutConsumer(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    @Override
    public void execute(Channel channel) throws Exception {


        // 如果没有该交换机者创建交换机
        // channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true,false,null);



        // 随机创建一个 queue
        String queueName = channel.queueDeclare().getQueue();

        System.out.println(queueName);

        String routingKey = "demo.#";

        // 通过随机创建的queue 与 交换机绑定
        channel.queueBind(queueName, exchangeName, routingKey);

        //String queueName = "hello13";

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "' routingKey："+envelope.getRoutingKey());
            }
        };
        channel.basicConsume(queueName, true, consumer);

        Thread.currentThread().join();

    }

    public static void main(String[] args){


        RabbitApplication.execute(new RabbitFanoutConsumer("logs.info"));

    }
}
