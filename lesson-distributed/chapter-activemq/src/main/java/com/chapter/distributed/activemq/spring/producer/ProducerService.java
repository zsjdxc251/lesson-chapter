package com.chapter.distributed.activemq.spring.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service

public class ProducerService{

    @Qualifier("queueJmsTemplate")
    @Autowired
    private JmsTemplate queueJmsTemplate;

    @Qualifier("topicJmsTemplate")
    @Autowired
    private JmsTemplate topicJmsTemplate;

//    @Qualifier("jmsTemplate")
//    @Autowired
    private JmsTemplate jmsTemplate;


    /*@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;*/

    public void sendMessage(){

        jmsTemplate.send("", session->{
            session.createTemporaryQueue();
            TextMessage textMessage = session.createTextMessage("");

            return textMessage;
        });
    }

    public void sendMessageTopic(String text) {
//        Destination queueDestination = new ActiveMQTopic("mytest.toip22222");
//        jmsTemplate.convertAndSend(queueDestination,text);

/*        jmsTemplate.send(queueDestination, session->{
            TextMessage textMessage = session.createTextMessage(text);
            System.out.println("发送消息："+text);
            return textMessage;
        });*/


        topicJmsTemplate.convertAndSend("mytest.topic",text);
    }

    public void sendMessageQueue(String text) {
//        Destination queueDestination = new ActiveMQQueue("mytest.queue22222");
//        jmsTemplate.convertAndSend(queueDestination,text);

/*        jmsTemplate.send(queueDestination, session->{
            TextMessage textMessage = session.createTextMessage(text);
            System.out.println("发送消息："+text);
            return textMessage;
        });*/

        /*queueJmsTemplate.send("mytest.queue22222",session -> {
            TextMessage textMessage = session.createTextMessage(text);
            textMessage.setStringProperty("domain","xxx.2");
            return textMessage;
        });*/
        queueJmsTemplate.convertAndSend("spring.mytest.queue",text);
    }

  /*  public void sendMessage1(String text) {
        Destination queueDestination = new ActiveMQTopic("mytest.toip");
        jmsMessagingTemplate.convertAndSend(queueDestination,text);


    }*/

}
