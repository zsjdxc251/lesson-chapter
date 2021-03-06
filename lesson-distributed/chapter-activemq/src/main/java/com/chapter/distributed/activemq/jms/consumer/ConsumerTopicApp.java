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
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/7/6.
 */
public class ConsumerTopicApp {

    private static final Logger log = LoggerFactory.getLogger(ConsumerTopicApp.class);


    public static void main(String[] args){

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://121.196.232.248:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            //延迟确认
            Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            // 创建目的地
            Destination destination = session.createTopic("mytopic");
            // 创建接收者
            MessageConsumer messageConsumer = session.createConsumer(destination);
            //Message message = messageConsumer.receive();
            //System.out.println(((TextMessage)message).getText());
            messageConsumer.setMessageListener(message -> {

                if (message instanceof TextMessage){

                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println(textMessage.getText());

                        textMessage.acknowledge();
                        //System.out.println(message.getJMSRedelivered());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (JMSException e) {
                        log.error(StringUtils.EMPTY,e);
                    } catch (InterruptedException e) {

                    }

                }
            });
        } catch (JMSException e) {
            log.error(StringUtils.EMPTY,e);
        }
    }
}
