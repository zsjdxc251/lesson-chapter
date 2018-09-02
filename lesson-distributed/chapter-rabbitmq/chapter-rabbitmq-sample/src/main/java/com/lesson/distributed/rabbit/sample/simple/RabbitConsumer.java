package com.lesson.distributed.rabbit.sample.simple;



import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class RabbitConsumer implements SampleHandler {

    @Override
    public void execute(Channel channel) {


        try {
            String QUEUE_NAME = "queue_name_warn";

            // queue : 队列名称    durable : 是否持久化   exclusive:      autoDelete: 是否删除   arquments
            // 没有的时候创建 queue
             //channel.queueDeclare(QUEUE_NAME, true, false, false, null);

             channel.queueBind(QUEUE_NAME," logs.error","dem.error");



            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(Thread.currentThread().getName()+" [x] Received '" + message + "'");
                }
            };

            channel.basicConsume(QUEUE_NAME, true, consumer);



            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{

        RabbitApplication.execute(new RabbitConsumer());

    }
}
