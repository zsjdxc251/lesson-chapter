package com.chapter.distributed.activemq.jms.provider;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author zhengshijun
 * @version created on 2018/7/6.
 */
public class ProviderTopicApp {

    private static final Logger log = LoggerFactory.getLogger(ProviderTopicApp.class);

    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://121.196.232.248:61616");

        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            //延迟确认
            Session session = connection.createSession(Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE );
            Destination destination = session.createTopic("mytopic");

            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            for (int i=0;i<10;i++) {
                TextMessage textMessage = session.createTextMessage("广播数据发送"+i);
                messageProducer.send(textMessage);
            }
            session.close();
        } catch (JMSException e) {
            log.error(StringUtils.EMPTY,e);
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    log.error(StringUtils.EMPTY,e);
                }
            }
        }

    }

}
