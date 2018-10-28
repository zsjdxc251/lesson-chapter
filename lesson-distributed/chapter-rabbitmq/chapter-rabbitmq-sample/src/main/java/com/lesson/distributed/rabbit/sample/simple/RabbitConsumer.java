package com.lesson.distributed.rabbit.sample.simple;



import com.google.common.collect.Maps;
import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RabbitConsumer implements SampleHandler {

    @Override
    public void execute(Channel channel) {


        try {
            String QUEUE_NAME = "hello-11";

            // queue : 队列名称    durable : 是否持久化   exclusive:      autoDelete: 是否删除   arquments
            // 没有的时候创建 queue
            Map<String, Object> arguments = new HashMap<>();
//            arguments.put("x-max-length",1);
//            arguments.put("x-dead-letter-exchange", "logs.error");
//            arguments.put("x-dead-letter-routing-key", "*.warn");
            arguments.put("x-max-priority", 10);
           channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);

             //channel.queueBind(QUEUE_NAME," color.blue","dem.error");
           //channel.queueBind(QUEUE_NAME,"",QUEUE_NAME);


            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(Thread.currentThread().getName()+" [x] Received '" + message + "' 优先级"+properties.getPriority());

//                   channel.basicAck(envelope.getDeliveryTag(),false);

                    channel.basicNack(envelope.getDeliveryTag(),false,true);

                }
            };


            channel.basicConsume(QUEUE_NAME, false,consumer);





            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{

        RabbitApplication.execute(new RabbitConsumer());

    }
}
