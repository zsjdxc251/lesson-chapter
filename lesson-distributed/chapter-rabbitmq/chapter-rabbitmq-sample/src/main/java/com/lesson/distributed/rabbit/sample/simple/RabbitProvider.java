package com.lesson.distributed.rabbit.sample.simple;

import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 *        创建queue 不指定交换机默认使用 {@code (AMQP default)}
 *        是使用该交换机
 *        <p>
 *            he default exchange is implicitly bound to every queue,
 *            with a routing key equal to the queue name.
 *            It is not possible to explicitly bind to,
 *            or unbind from the default exchange. It also cannot be deleted.
 *        <p/>
 *
 *
 *         exchange 默认隐式绑定到 和 queue相同名的 routingKey上
 *
 *
 *         所以在发送端需要 需要配置 routingKey = queueName 否则只是发送到了交换机上面
 *
 *         也指定了queueName ，但是exchange 需要通过 routingKey 路由到 到 queueName上
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */



public class RabbitProvider implements SampleHandler {


    @Override
    public void execute(Channel channel) {
        try {
            channel.queueDeclare("hello-1", true, false, false, null);



            for (int i = 0; i < 10; i++) {
                channel.basicPublish(StringUtils.EMPTY, "",
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
