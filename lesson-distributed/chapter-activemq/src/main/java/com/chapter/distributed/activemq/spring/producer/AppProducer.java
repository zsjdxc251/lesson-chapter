package com.chapter.distributed.activemq.spring.producer;


import com.chapter.distributed.activemq.spring.CoreConfigure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.stream.IntStream;

@Configuration
@ComponentScan("com.chapter.distributed.activemq.spring.producer")
@Import({CoreConfigure.class})
public class AppProducer {



    @Bean(name = "queueJmsTemplate")
    public JmsTemplate queueJmsTemplate(SingleConnectionFactory singleConnectionFactory, MessageConverter messageConverter){

        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(singleConnectionFactory);
        jmsTemplate.setMessageConverter(messageConverter);

        return jmsTemplate;
    }
    @Bean(name = "topicJmsTemplate")
    public JmsTemplate topicJmsTemplate(SingleConnectionFactory singleConnectionFactory, MessageConverter messageConverter){

        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(singleConnectionFactory);
        jmsTemplate.setMessageConverter(messageConverter);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

//    @Bean(name = "jmsTemplate")
//    public JmsTemplate jmsTemplate(SingleConnectionFactory singleConnectionFactory, MessageConverter messageConverter){
//
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(singleConnectionFactory);
//        jmsTemplate.setMessageConverter(messageConverter);
//        return jmsTemplate;
//    }

   /* @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(SingleConnectionFactory connectionFactory,SimpleMessageConverter simpleMessageConverter){
        JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate(connectionFactory);
        jmsMessagingTemplate.setMessageConverter(simpleMessageConverter);
        return jmsMessagingTemplate;
    }*/



    public static void main(String[] args){
        ApplicationContext app = new AnnotationConfigApplicationContext(AppProducer.class);

        ProducerService producerService = app.getBean(ProducerService.class);

        IntStream.range(0,1000).forEach(x->{
            try {
                System.out.println(x);
                Thread.sleep(500);
                producerService.sendMessageQueue("队列 xxxx"+x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        IntStream.range(0,10).forEach(x->{
//            producerService.sendMessageTopic("广播 xxxx"+x);
//        });




    }
}
