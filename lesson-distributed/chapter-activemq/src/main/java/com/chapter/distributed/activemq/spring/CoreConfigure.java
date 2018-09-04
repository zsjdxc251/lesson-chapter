package com.chapter.distributed.activemq.spring;


import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import com.chapter.distributed.activemq.spring.converter.CustomMessageConverter;

@EnableJms
public class CoreConfigure {


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
//        activeMQConnectionFactory.setUserName("weway");
//        activeMQConnectionFactory.setPassword("ww123456");
        return activeMQConnectionFactory;
    }


    /*@Bean
    @DependsOn({"activeMQConnectionFactory"})
    public SingleConnectionFactory singleConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory();
        singleConnectionFactory.setTargetConnectionFactory(activeMQConnectionFactory);
        return singleConnectionFactory;
    }*/
    @DependsOn({"activeMQConnectionFactory"})
    @Bean
    public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
        cachingConnectionFactory.setSessionCacheSize(100);
        return cachingConnectionFactory;
    }

//    @Bean(name = "messageConverter")
//    public SimpleMessageConverter simpleMessageConverter(){
//
//        SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
//
//        return simpleMessageConverter;
//    }
    @Bean(name = "messageConverter")
    public CustomMessageConverter customMessageConverter(){
        CustomMessageConverter customMessageConverter = new CustomMessageConverter();
        return customMessageConverter;
    }
}
