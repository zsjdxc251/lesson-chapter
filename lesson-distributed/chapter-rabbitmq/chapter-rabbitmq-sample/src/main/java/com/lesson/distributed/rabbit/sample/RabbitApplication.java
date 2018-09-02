package com.lesson.distributed.rabbit.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitApplication {

    public static void execute(Class<SampleHandler> sampleHandlerClass) {
        try {
            SampleHandler sampleHandler = sampleHandlerClass.newInstance();

            execute(sampleHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void execute(SampleHandler sampleHandler) {
        Channel channel = null;
        Connection connection = null;
        ConnectionFactory factory = null;
        try {
            // 创建连接工厂
            factory = new ConnectionFactory();
            // 设置连接ip
            //factory.setHost("192.168.204.132");
            factory.setHost("121.196.232.248");
            factory.setPort(5672);
            factory.setVirtualHost("/");
            factory.setConnectionTimeout(500000);
            factory.setUsername("guest");
            factory.setPassword("guest");
            // 获取连接
            connection = factory.newConnection();

            // 创建通道
            channel = connection.createChannel();

            if (sampleHandler == null){
                throw new NullPointerException();
            }

            try {
                sampleHandler.execute(channel);
            } catch (Exception e) {
                e.printStackTrace();
            }

            channel.close();
            connection.close();
            factory.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
