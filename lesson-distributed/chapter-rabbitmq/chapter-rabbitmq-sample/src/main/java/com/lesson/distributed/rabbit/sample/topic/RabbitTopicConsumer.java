package com.lesson.distributed.rabbit.sample.topic;

import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.*;

import java.io.IOException;


public class RabbitTopicConsumer implements SampleHandler {


    private final String exchangeName ;

    public RabbitTopicConsumer(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    @Override
    public void execute(Channel channel) throws Exception {


        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC);

        String queueName = channel.queueDeclare().getQueue();

        String routingKey = "";
        channel.queueBind(queueName,exchangeName,routingKey);




        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);






    }


    public static void main(String[] args){

    }
}
