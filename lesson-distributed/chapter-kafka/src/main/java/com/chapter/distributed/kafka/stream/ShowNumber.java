package com.chapter.distributed.kafka.stream;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
public class ShowNumber {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "121.196.232.248:9092,121.196.232.248:9093,121.196.232.248:9094");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        final KafkaConsumer<String, Long> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("CountSendWordsNum"),new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //将偏移设置到最开始
                consumer.seekToBeginning(collection);
            }
        });
        while (true) {
            ConsumerRecords<String, Long> records = consumer.poll(100);
            for (ConsumerRecord<String, Long> record : records) {
                System.out.println(""+record.offset() +" "+ record.key()+" " + record.value() +"\n");
            }
        }
    }

}
