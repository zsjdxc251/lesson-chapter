package com.chapter.distributed.kafka.spring.anno.consumer;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpoint;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.TopicPartitionInitialOffset;

import java.util.Collection;

/**
 * @author zhengshijun
 * @version created on 2018/10/24.
 */
public class CustomKafkaListenerContainerFactory<K,V> extends ConcurrentKafkaListenerContainerFactory<K,V> {

    @Override
    protected ConcurrentMessageListenerContainer createContainerInstance(KafkaListenerEndpoint endpoint) {

        Collection<TopicPartitionInitialOffset> topicPartitions = endpoint.getTopicPartitions();
        if (!topicPartitions.isEmpty()) {
            ContainerProperties properties = new ContainerProperties(
                    topicPartitions.toArray(new TopicPartitionInitialOffset[topicPartitions.size()]));
            properties.setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
            return new ConcurrentMessageListenerContainer<K, V>(getConsumerFactory(), properties);
        }
        else {
            Collection<String> topics = endpoint.getTopics();
            if (!topics.isEmpty()) {
                ContainerProperties properties = new ContainerProperties(topics.toArray(new String[topics.size()]));
                properties.setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
                properties.setCommitCallback((v1,v2)->{
                    System.out.println("v1");
                });
                ConcurrentMessageListenerContainer concurrentMessageListenerContainer =  new ConcurrentMessageListenerContainer<K, V>(getConsumerFactory(), properties);
                return concurrentMessageListenerContainer;
            }
            else {
                ContainerProperties properties = new ContainerProperties(endpoint.getTopicPattern());
                properties.setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);

                return new ConcurrentMessageListenerContainer<K, V>(getConsumerFactory(), properties);
            }
        }
    }
}
