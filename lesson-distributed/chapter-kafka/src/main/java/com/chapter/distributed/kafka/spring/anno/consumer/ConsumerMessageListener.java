package com.chapter.distributed.kafka.spring.anno.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
public class ConsumerMessageListener implements MessageListener<Integer,String> {

    @Override
    public void onMessage(ConsumerRecord<Integer, String> data) {
        System.out.println(data.value());
    }
}
