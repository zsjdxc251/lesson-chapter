package com.chapter.distributed.kafka.sample;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/7/13.
 */
public class ConsumerApp extends Thread{
    private static final Logger log = LoggerFactory.getLogger(ConsumerApp.class);

    private final KafkaConsumer<Integer,String> consumer;


    private ConsumerApp(String topic) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"47.114.189.36:3389");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"kafka_sample_consumer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"0");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"5");

        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topic));

        // 只消费指定分区消息
//        TopicPartition topicPartition=new TopicPartition(topic,1);
//        consumer.assign(Arrays.asList(topicPartition));



    }

    @Override
    public void run() {

        ConsumerRecords<Integer,String> consumerRecord=consumer.poll(200000);
        log.info("consumerRecord size:{}",consumerRecord.count());
        for(ConsumerRecord record:consumerRecord){
            log.info("id:{},value:{},partition:{},offset:{}",record.key(),record.value(),record.partition(),record.offset());
            //consumer.commitSync();
        }


        consumer.close();

    }

    public static void main(String[] args){
        //同一个topic，同一个groupId中 消费者会均匀消费 如果有三个分区有三个消费者则一个消费者一个分区
        //
        new ConsumerApp("test").start();
        System.out.println("kafka_sample_consumer".hashCode()%50);
    }
}
