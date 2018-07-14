package com.chapter.distributed.kafka.spring.anno.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProducerConfigure.class})
public class ProducerConfigureTest {

    @Autowired
    private KafkaTemplate<Integer,String> kafkaTemplate;


    @Test
    public void test1(){
        ProducerRecord<Integer,String> producerRecord = new ProducerRecord<>("test","spring 数据");
        ListenableFuture<SendResult<Integer, String>> result =  kafkaTemplate.send(producerRecord);
        result.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>(){
            @Override
            public void onFailure(Throwable ex) {

            }
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println(result.getRecordMetadata().offset());

            }
        });

    }

}