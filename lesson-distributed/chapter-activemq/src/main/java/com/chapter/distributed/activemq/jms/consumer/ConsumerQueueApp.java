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

/**
 * @author zhengshijun
 * @version created on 2018/7/6.
 */
public class ConsumerQueueApp {

    private static final Logger log = LoggerFactory.getLogger(ConsumerQueueApp.class);

    public static void main(String[] args){


        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("weway","ww123456","tcp://192.168.1.28:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            //延迟确认
            Session session = connection.createSession(Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE);
            Destination destination = session.createQueue("myqueue");
            MessageConsumer messageConsumer = session.createConsumer(destination);
            //Message message = messageConsumer.receive();
            //System.out.println(((TextMessage)message).getText());
            messageConsumer.setMessageListener(message -> {

                if (message instanceof TextMessage){

                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println(textMessage.getText());

                        //textMessage.acknowledge();
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
