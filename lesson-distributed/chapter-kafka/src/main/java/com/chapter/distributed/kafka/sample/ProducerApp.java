package com.chapter.distributed.kafka.sample;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author zhengshijun
 * @version created on 2018/7/13.
 */
public class ProducerApp extends Thread{
    private static final Logger log = LoggerFactory.getLogger(ProducerApp.class);

    private final KafkaProducer<Integer,String> producer;
    private final String topic;
    private final boolean isAysnc;


    private ProducerApp(String topic,boolean isAysnc) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"121.196.232.248:9092,121.196.232.248:9093,121.196.232.248:9094");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"kafka_sample_producer");
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // 消息发送到哪个分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.chapter.distributed.kafka.sample.CustomPartitioner");
        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
        this.isAysnc = isAysnc;
    }

    @Override
    public void run() {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i=0;i<10000;i++) {
            String message = "数据"+i;
            log.info("message:{}",message);
            if (isAysnc){
                // 异步发送数据
                producer.send(new ProducerRecord<>(topic,message),(recordMetadata,exception)->{
                    log.info("isDaemon:{} async-offset:{}->partition:{}",Thread.currentThread().isDaemon(),recordMetadata.offset(),recordMetadata.partition());
                    countDownLatch.countDown();

                });
            } else {
                try {
                    RecordMetadata recordMetadata=producer.send(new ProducerRecord<>(topic,message)).get();
                    log.info("sync-offset:{}->partition:{}",recordMetadata.offset(),recordMetadata.partition());
                } catch (InterruptedException | ExecutionException e) {
                    log.error(StringUtils.EMPTY,e);
                } finally {
                    countDownLatch.countDown();
                }
            }
        }
        try {
            countDownLatch.await();
        }catch (InterruptedException e) {
            log.error(StringUtils.EMPTY,e);
        }

    }
    public static void main(String[] args){
        new ProducerApp("secondTopic",false).start();

    }
}
