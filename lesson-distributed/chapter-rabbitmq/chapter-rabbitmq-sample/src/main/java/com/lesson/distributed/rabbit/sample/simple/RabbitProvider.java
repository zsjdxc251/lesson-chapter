package com.lesson.distributed.rabbit.sample.simple;

import com.google.common.collect.Maps;
import com.lesson.distributed.rabbit.sample.RabbitApplication;
import com.lesson.distributed.rabbit.sample.SampleHandler;
import com.rabbitmq.client.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

            // 如果没有该queue则会创建该 queue

            String queueName = "hello-11";


            Map<String, Object> arguments = new HashMap<>();

            //arguments.put("x-expires",10000);
            //channel.queueDeclare(queueName, true, false, false, arguments);




            channel.confirmSelect();

            AMQP.BasicProperties props = new AMQP.BasicProperties().builder().deliveryMode(2).build();
            for (int i = 0; i < 10; i++) {
                final int index = i;
                channel.basicPublish(StringUtils.EMPTY, queueName,
                        props, ("等过期消息" + index).getBytes());
            }
            channel.addConfirmListener(new ConfirmListener() {
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("Broker未确认消息，标识：" + deliveryTag);
                }
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    // 如果true表示批量执行了deliveryTag这个值以前（小于deliveryTag的）的所有消息，如果为false的话表示单条确认
                    System.out.println(String.format("Broker已确认消息，标识：%d，多个消息：%b", deliveryTag, multiple));
                }
            });






            Thread.currentThread().join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        RabbitApplication.execute(new RabbitProvider());

    }
}
