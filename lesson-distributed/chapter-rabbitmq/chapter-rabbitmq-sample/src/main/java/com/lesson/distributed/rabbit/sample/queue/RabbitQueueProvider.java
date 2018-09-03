package com.lesson.distributed.rabbit.sample.queue;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;


/**
 *
 * @author zhengshijun
 *
 *
 *
 */
public class RabbitQueueProvider implements SampleHandler {

    private final String exchangeName;
    private final String routingKey;



    public RabbitQueueProvider(String exchangeName, String routingKey) {
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;

    }

    @Override
    public void execute(Channel channel) throws Exception {


        String message = exchangeName+"/发送消息";


        channel.basicPublish(exchangeName, routingKey,
                MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

    }
    public static void main(String[] args){
        String exchangeName = "color.pink";
        String routingKey = "xxx";
        SampleHandler sampleHandler = new RabbitQueueProvider(exchangeName,routingKey);
        RabbitApplication.execute(sampleHandler);


    }
}
