package com.chapter.distributed.kafka.spring.anno.consumer;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.Metadata;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
@Configuration
@EnableKafka
@ComponentScan("com.chapter.distributed.kafka.spring.anno.consumer")
public class ConsumerConfigure {


    private Map<String,Object> properties(){
        Map<String,Object> properties = Maps.newHashMap();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"121.196.232.248:9092,121.196.232.248:9093,121.196.232.248:9094");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"kafka_sample_consumer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");

        return properties;
    }


    private  <K,V> ConsumerFactory<K,V> consumerFactory(){
        DefaultKafkaConsumerFactory<K,V> consumerFactory = new DefaultKafkaConsumerFactory<K,V>(properties());
        return consumerFactory;
    }
//    @Bean
//    public <K,V> ConcurrentKafkaListenerContainerFactory<K, V> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<K, V> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }



    @Bean(initMethod = "doStart")
    public <K,V> KafkaMessageListenerContainer kafkaMessageListenerContainer(){
        KafkaMessageListenerContainer<K,V> kafkaMessageListenerContainer = new KafkaMessageListenerContainer<>(consumerFactory(),containerProperties());
        return kafkaMessageListenerContainer;
    }
    private ContainerProperties containerProperties(){
        ContainerProperties containerProperties = new ContainerProperties("test");
        containerProperties.setMessageListener(new ConsumerMessageListener());
        return containerProperties;

    }







}
