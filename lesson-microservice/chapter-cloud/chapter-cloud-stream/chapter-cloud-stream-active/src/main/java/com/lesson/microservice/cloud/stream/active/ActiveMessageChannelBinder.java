package com.lesson.microservice.cloud.stream.active;

import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.messaging.MessageChannel;

/**
 * @author zhengshijun
 * @version created on 2018/8/23.
 */
public class ActiveMessageChannelBinder implements Binder<MessageChannel, ConsumerProperties, ProducerProperties> {

    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inboundBindTarget, ConsumerProperties consumerProperties) {
        return null;
    }

    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outboundBindTarget, ProducerProperties producerProperties) {
        return null;
    }
}
