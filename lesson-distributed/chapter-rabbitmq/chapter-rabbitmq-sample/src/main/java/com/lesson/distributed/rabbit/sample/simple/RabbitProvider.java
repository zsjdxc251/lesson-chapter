package com.lesson.distributed.rabbit.sample.simple;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.apache.commons.lang3.StringUtils;



public class RabbitProvider implements SampleHandler {


    @Override
    public void execute(Channel channel) {
        try {
            channel.queueDeclare("hello", false, false, false, null);



            for (int i = 0; i < 10; i++) {
                channel.basicPublish(StringUtils.EMPTY, "12345",
                        MessageProperties.PERSISTENT_TEXT_PLAIN, ("消息" + i).getBytes());


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        RabbitApplication.execute(new RabbitProvider());

    }
}
