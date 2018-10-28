package com.lesson.distributed.rabbit.sample.fanout;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.apache.commons.lang3.StringUtils;


/**
 *
 *    广播
 *
 *    使用该示例 不需要人工创建QueueName 系统会随机创建指定QueueName
 *
 *
 *    exchangeName + routingKey (可以为空为临时queueName) 组成一个 queueName
 *
 *
 *    一个exchangeName 有多个 routingKey 一个
 *
 *
 * @author zhengshijun
 *
 */
public class RabbitFanoutProvider implements SampleHandler {


    private final String exchangeName;


    public RabbitFanoutProvider(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    @Override
    public void execute(Channel channel) throws Exception {




        //channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);

        String message = exchangeName+"/发送消息";
        String routingKey = "demo.22";
        channel.basicPublish(exchangeName, routingKey,
                MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());



    }

    public static void main(String[] args){


        RabbitApplication.execute(new RabbitFanoutProvider("logs.info"));

    }
}
