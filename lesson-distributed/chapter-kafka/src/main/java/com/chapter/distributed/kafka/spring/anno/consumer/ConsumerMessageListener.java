package com.chapter.distributed.kafka.spring.anno.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
public class ConsumerMessageListener implements AcknowledgingMessageListener<Integer,String> {

    @Override
    public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {
        System.out.println(data.offset());
        System.out.println("onMessage acknowledgment:"+data.value());
        acknowledgment.acknowledge();


    }


}
