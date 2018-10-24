package com.chapter.distributed.kafka.spring.anno.consumer;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

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

//        earliest
//        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
//                latest
//        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
//                none
//        topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"10000");

        return properties;
    }


    private  <K,V> ConsumerFactory<K,V> consumerFactory(){
        DefaultKafkaConsumerFactory<K,V> consumerFactory = new DefaultKafkaConsumerFactory<K,V>(properties());

        return consumerFactory;
    }
    @Bean
    public <K,V> ConcurrentKafkaListenerContainerFactory<K, V> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory =
                new CustomKafkaListenerContainerFactory<K,V>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }



//    @Bean(initMethod = "doStart")
//    public <K,V> KafkaMessageListenerContainer kafkaMessageListenerContainer(){
//        KafkaMessageListenerContainer<K,V> kafkaMessageListenerContainer = new KafkaMessageListenerContainer<>(consumerFactory(),containerProperties());
//        return kafkaMessageListenerContainer;
//    }
//    private ContainerProperties containerProperties(){
//        ContainerProperties containerProperties = new ContainerProperties("test");
//        containerProperties.setMessageListener(new ConsumerMessageListener());
//        containerProperties.setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
//        containerProperties.setCommitCallback((var1,var2)->{
//
//        });
//        return containerProperties;
//
//    }







}
