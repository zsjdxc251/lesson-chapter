package com.chapter.distributed.activemq.jms.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *  持久订阅
 *   广播可消费持久化消费
 *   可以消费在启动消费者之前发出的消息
 * @author zhengshijun
 * @version created on 2018/7/7.
 */
public class ConsumerPersistentTopicApp {
    private static final Logger log = LoggerFactory.getLogger(ConsumerPersistentTopicApp.class);
    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("weway","ww123456","tcp://192.168.1.28:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("9966_1");
            connection.start();

            //延迟确认
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 创建目的地
            Topic destination = session.createTopic("mytopic");
            // 创建接收者
            MessageConsumer messageConsumer = session.createDurableSubscriber(destination,"9966");

            messageConsumer.setMessageListener(message -> {
                if (message instanceof TextMessage){

                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        log.error(StringUtils.EMPTY,e);
                    }
                }
            });
        } catch (JMSException e) {
            log.error(StringUtils.EMPTY,e);
        }
    }

}
