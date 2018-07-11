package com.chapter.distributed.activemq.jms.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/7/6.
 */
public class ConsumerQueueApp {

    private static final Logger log = LoggerFactory.getLogger(ConsumerQueueApp.class);

    public static void main(String[] args){


        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://121.196.232.248:61616");
        Connection connection = null;
        try {

            //connectionFactory.setTransactedIndividualAck(true);

            connectionFactory.setOptimizeAcknowledge(true);
//            connectionFactory.setUseAsyncSend(true);
            connection = connectionFactory.createConnection();

            connection.start();

            //延迟确认
            Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            Destination destination = session.createQueue("myqueue?consumer.prefetchSize=10");
            MessageConsumer messageConsumer = session.createConsumer(destination);

//            TextMessage textMessage = (TextMessage) messageConsumer.receive();
//            System.out.println(textMessage.getText());
//            textMessage.acknowledge();

            messageConsumer.setMessageListener(message -> {

                if (message instanceof TextMessage){

                    try {
                        TextMessage textMessage = (TextMessage)message;
                        System.out.println(textMessage.getText());

//                        //throw new RuntimeException();
                    } catch (JMSException e ) {
                        log.error(StringUtils.EMPTY,e);
                    }

                }
            });

        } catch (JMSException e) {
            log.error(StringUtils.EMPTY,e);
        }

    }
}
