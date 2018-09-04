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
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * @author zhengshijun
 * @version created on 2018/7/6.
 */
public class ProviderTopicApp {

    private static final Logger log = LoggerFactory.getLogger(ProviderTopicApp.class);

    public static void main(String[] args){

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
//        ((ActiveMQConnectionFactory) connectionFactory).setPassword("ww123456");
//        ((ActiveMQConnectionFactory) connectionFactory).setUserName("weway");


        try {
            final Connection connection = connectionFactory.createConnection();
            connection.setClientID("client_id"+ UUID.randomUUID().toString());
            connection.start();



            IntStream.range(1,1000).forEach((x)->{

                System.out.println(x);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {

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
                } catch (Exception e) {
                    e.printStackTrace();
                }



            });




        } catch (JMSException e) {
            log.error(StringUtils.EMPTY,e);
        } finally {
//            if (connection != null){
//                try {
//                    connection.close();
//                } catch (JMSException e) {
//                    log.error(StringUtils.EMPTY,e);
//                }
//            }
        }





    }

}
