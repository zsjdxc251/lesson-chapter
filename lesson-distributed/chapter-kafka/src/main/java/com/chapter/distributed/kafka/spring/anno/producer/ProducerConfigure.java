package com.chapter.distributed.kafka.spring.anno.producer;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;
import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
@Configuration
public class ProducerConfigure {

    private Map<String,Object> properties(){
        Map<String,Object> properties = Maps.newHashMap();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"121.196.232.248:9092,121.196.232.248:9093,121.196.232.248:9094");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"kafka_sample_producer");
        properties.put(ProducerConfig.ACKS_CONFIG,"-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }

    @Bean
    public <K, V> DefaultKafkaProducerFactory producerFactory(){
        DefaultKafkaProducerFactory<K, V> producerFactory = new DefaultKafkaProducerFactory<K,V>(properties());
        return producerFactory;
    }

    @Bean
    public <K, V> KafkaTemplate kafkaTemplate(ProducerFactory<K, V> producerFactory){
        KafkaTemplate<K, V> kafkaTemplate = new KafkaTemplate<>(producerFactory,true);
        return kafkaTemplate;
    }
}
