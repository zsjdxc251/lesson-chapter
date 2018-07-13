package com.chapter.distributed.kafka.sample;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/7/13.
 */
public class ConsumerApp extends Thread{

    private final KafkaConsumer<Integer,String> consumer;



    public ConsumerApp(String topic) {

        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"121.196.232.248:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"kafka_sample_consumer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        //properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        //properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        //properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topic));
    }

    @Override
    public void run() {

        ConsumerRecords<Integer,String> consumerRecord=consumer.poll(1000);
        for(ConsumerRecord record:consumerRecord){
            System.out.println("message receive:"+record.value());
        }

    }

    public static void main(String[] args){
        new ConsumerApp("test").start();
    }
}
