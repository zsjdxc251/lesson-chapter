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
import java.util.stream.IntStream;

/**
 * @author zhengshijun
 * @version created on 2018/7/6.
 */
public class ProviderQueueApp {

    private static final Logger log = LoggerFactory.getLogger(ProviderQueueApp.class);

    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.23.129:61616");
        Connection connection = null;
        try {
            //((ActiveMQConnectionFactory) connectionFactory).setUseAsyncSend(false);
            connection = connectionFactory.createConnection();
            connection.start();

            //延迟确认
            Session session = connection.createSession(Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE );
            Destination destination = session.createQueue("myqueue");

            MessageProducer messageProducer = session.createProducer(destination);
            // 是否持久化
//            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);


            for (int i=0;i<10;i++) {
                TextMessage textMessage = session.createTextMessage("数据发送"+i);
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
