package com.lesson.microservice.cloud.stream.active;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author zhengshijun
 * @version created on 2018/8/23.
 */
public class ActiveMessageChannelBinder implements Binder<MessageChannel, ConsumerProperties, ProducerProperties> {
    private static final Logger log = LoggerFactory.getLogger(ActiveMessageChannelBinder.class);

    /**
     *
     * @param name destination
     * @param group group
     * @param inboundBindTarget
     * @param consumerProperties
     * @return
     */
    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inboundBindTarget, ConsumerProperties consumerProperties) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.28:61616");
        connectionFactory.setUserName("weway");
        connectionFactory.setPassword("ww123456");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(name);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(message -> {
                TextMessage textMessage = TextMessage.class.cast(message);
                // 发送消息到 消息管道
                try {
                    inboundBindTarget.send(new GenericMessage<String>(textMessage.getText()));
                } catch (JMSException e) {
                    log.error(StringUtils.EMPTY,e);
                }
            });
        } catch (JMSException e) {
            log.error(StringUtils.EMPTY,e);
        }
        return ()->{

        };
    }

    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outboundBindTarget, ProducerProperties producerProperties) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.28:61616");
        connectionFactory.setUserName("weway");
        connectionFactory.setPassword("ww123456");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(name);
            MessageProducer messageProducer = session.createProducer(destination);
            SubscribableChannel subscribableChannel = (SubscribableChannel) outboundBindTarget;
            // 消息订阅回调
            subscribableChannel.subscribe(message -> {
                // 消息主题
                Object messageBody = message.getPayload();
                try {

                    TextMessage textMessage = session.createTextMessage(new String(byte[].class.cast(messageBody)));
                    messageProducer.send(textMessage);
                } catch (JMSException e) {
                    log.error(StringUtils.EMPTY,e);
                }
            });
        } catch (JMSException e) {
            log.error(StringUtils.EMPTY,e);
        }
        return ()->{

        };
    }
}
