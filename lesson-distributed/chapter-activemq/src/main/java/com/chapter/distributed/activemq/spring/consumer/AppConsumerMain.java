package com.chapter.distributed.activemq.spring.consumer;


import com.chapter.distributed.activemq.spring.CoreConfigure;

import com.chapter.distributed.activemq.spring.InitialBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.Session;


@Configuration
@ComponentScan("com.chapter.distributed.activemq.spring.consumer")
@Import({CoreConfigure.class, InitialBean.class})
@EnableJms
public class AppConsumerMain {


//    @Bean
//    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(SingleConnectionFactory singleConnectionFactory){
//
//        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
//        defaultJmsListenerContainerFactory.setConnectionFactory(singleConnectionFactory);
//        defaultJmsListenerContainerFactory.setPubSubDomain(true);
//        return defaultJmsListenerContainerFactory;
//
//    }

//    @Bean
//    public ConsumerMessageListener consumerMessageListener(){
//
//        ConsumerMessageListener consumerMessageListener = new ConsumerMessageListener();
//
//        return consumerMessageListener;
//    }
//
//
//    @Bean
//    public DefaultMessageListenerContainer defaultMessageListenerContainer(ConsumerMessageListener consumerMessageListener,
//                                                                           SingleConnectionFactory singleConnectionFactory){
//        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
//        defaultMessageListenerContainer.setMessageListener(consumerMessageListener);
//        defaultMessageListenerContainer.setConnectionFactory(singleConnectionFactory);
//
//        defaultMessageListenerContainer.setDestinationName("mytest.toip");
//        return defaultMessageListenerContainer;
//
//    }

    @Bean(name = "topicContainer")
    public JmsListenerContainerFactory<SimpleMessageListenerContainer> topicJmsListenerContainerFactory(SingleConnectionFactory singleConnectionFactory, MessageConverter messageConverter){

        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setPubSubDomain(true);
        simpleJmsListenerContainerFactory.setConnectionFactory(singleConnectionFactory);
        simpleJmsListenerContainerFactory.setMessageConverter(messageConverter);
        simpleJmsListenerContainerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);

        //SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();

        return simpleJmsListenerContainerFactory;
    }

    @Bean(name = "queueContainer")
    public JmsListenerContainerFactory<SimpleMessageListenerContainer> queueJmsListenerContainerFactory(SingleConnectionFactory singleConnectionFactory, MessageConverter messageConverter){

        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setPubSubDomain(false);
        simpleJmsListenerContainerFactory.setConnectionFactory(singleConnectionFactory);
        simpleJmsListenerContainerFactory.setMessageConverter(messageConverter);
        simpleJmsListenerContainerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);

        return simpleJmsListenerContainerFactory;
    }






    public static void main(String[] args) throws Exception{
        ApplicationContext app = new AnnotationConfigApplicationContext(AppConsumerMain.class);


    }
}
