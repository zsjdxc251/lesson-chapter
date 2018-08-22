package com.lesson.microservice.boot.sample.microservice.cloud.bus.consumer;

import com.lesson.microservice.boot.sample.microservice.cloud.bus.api.MessageRemoteApplicationEvent;
import org.springframework.context.ApplicationListener;

public class CustomApplicationListener implements ApplicationListener<MessageRemoteApplicationEvent> {

    @Override
    public void onApplicationEvent(MessageRemoteApplicationEvent event) {


        System.out.println("收到远程消息:"+event.getMessage());
    }
}
