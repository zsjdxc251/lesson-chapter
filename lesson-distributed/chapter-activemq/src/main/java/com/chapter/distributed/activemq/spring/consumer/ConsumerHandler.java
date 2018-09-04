package com.chapter.distributed.activemq.spring.consumer;


import com.chapter.distributed.activemq.spring.QueueJmsListener;
import com.chapter.distributed.activemq.spring.TopicJmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class ConsumerHandler {




    /**
     *  concurrency 动态线程数
     *
     * @param message
     * @throws Exception
     */
    @TopicJmsListener(destination = "mytest.topic")
    //@TopicJmsListener(destination = Constant.KEY,containerFactory = "topicContainer",selector = "domain='xxx.1'",concurrency = "2-8")
    //@JmsListener(destination = "mytest.topic",containerFactory = "topicContainer")
    public void receiveTopic(Message message) throws Exception {
//        try {
//            TextMessage textMessage = (TextMessage)message;
//            System.out.println("Consumer 广播 收到的报文为:"+textMessage.getText());
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }

        System.out.println("Consumer 广播 收到的报文为:"+((TextMessage)message).getText()+";domain:"+(message.getStringProperty("domain")));

    }


    @QueueJmsListener(destination = "spring.mytest.queue")
    public void receiveQueue(Message message) throws Exception{
//        try {
//            TextMessage textMessage = (TextMessage)message;
//            System.out.println("Consumer 队列 收到的报文为:"+textMessage.getText());
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
        System.out.println("Consumer spring.mytest.queue 队列 收到的报文为:"+((TextMessage)message).getText()+";domain :"+(message.getStringProperty("domain")));

    }
}
