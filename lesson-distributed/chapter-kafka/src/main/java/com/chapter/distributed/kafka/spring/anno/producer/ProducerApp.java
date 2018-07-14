package com.chapter.distributed.kafka.spring.anno.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
public class ProducerApp {

    public static void main(String[] args) {

        ApplicationContext app = new AnnotationConfigApplicationContext(ProducerConfigure.class);

        KafkaTemplate<Integer, String> kafkaTemplate = app.getBean(KafkaTemplate.class);
        ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>("test", "spring 数据");

        // 异步发送
        ListenableFuture<SendResult<Integer, String>> result = kafkaTemplate.send(producerRecord);
        result.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();

            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println(result.getRecordMetadata().offset());

            }


        });


        // 批量事务发送
//        kafkaTemplate.executeInTransaction((operations)->{
//            //operations.send()
//            //
//            return true;
//        });

    }
}
