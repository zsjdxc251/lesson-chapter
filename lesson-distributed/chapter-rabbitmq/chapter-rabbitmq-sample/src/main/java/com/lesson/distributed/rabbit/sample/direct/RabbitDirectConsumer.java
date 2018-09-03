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

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,true);

        String queueName = channel.queueDeclare().getQueue();
        log.info("queueName:{}",queueName);
    
        String routingKey = "*.warn";
        if (StringUtils.isNotBlank(routingKey)){
            channel.queueBind(queueName,exchangeName,routingKey);

        }

        // String destination, String source, String routingKey
        // 交换机绑定交换机   在消息发送重交换机 source , routingKey 发送消息 绑定到  名词为 destination 的交换机上面
        // channel.exchangeBind()

        // String queue, String exchange, String routingKey
        // 队列名称绑定到 交换机上面
        //channel.queueBind()



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

        RabbitApplication.execute(new RabbitDirectConsumer("logs.error"));

    }
}
