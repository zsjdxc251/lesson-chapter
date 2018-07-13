package com.chapter.distributed.kafka.sample;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/7/13.
 */
public class ProducerApp extends Thread{
    private static final Logger log = LoggerFactory.getLogger(ProducerApp.class);

    private final KafkaProducer<Integer,String> producer;
    private final String topic;

    public ProducerApp(String topic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"121.196.232.248:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"kafka_sample_producer");
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {

        for (int i=0;i<100;i++) {
            String message = "数据"+i;
            try {
                RecordMetadata recordMetadata=producer.
                        send(new ProducerRecord<Integer, String>(topic,message)).get();
                System.out.println("sync-offset:"+recordMetadata.offset()+
                        "->partition"+recordMetadata.partition());
            } catch (Exception e) {
                log.error(StringUtils.EMPTY,e);
            }
        }

    }
    public static void main(String[] args){
        new ProducerApp("test").start();

    }
}
