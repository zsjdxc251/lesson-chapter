package com.chapter.distributed.activemq.spring.consumer;

import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

//@Service
public class ConsumerService implements JmsListenerConfigurer {


    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar jmsListenerEndpointRegistrar) {

        SimpleJmsListenerEndpoint simpleJmsListenerEndpoint = new SimpleJmsListenerEndpoint();
        simpleJmsListenerEndpoint.setId("simpleJmsListenerEndpointId");
        simpleJmsListenerEndpoint.setDestination("mytest.toip22222");
        simpleJmsListenerEndpoint.setMessageListener(message->{


            System.out.println("接收到信息："+message);


        });

        jmsListenerEndpointRegistrar.registerEndpoint(simpleJmsListenerEndpoint);
    }
}
