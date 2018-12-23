package com.lesson.microservice.boot.sample.microservice.cloud.bus.provider.controller;


import com.lesson.microservice.boot.sample.microservice.cloud.bus.api.MessageRemoteApplicationEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController implements ApplicationEventPublisherAware {

    @Autowired
    private ApplicationContext applicationContext;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private BusProperties busProperties;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(String message){


        applicationContext.getId();
        applicationContext.getDisplayName();
        applicationContext.getApplicationName();
        String[] names = {applicationContext.getId(),
                applicationContext.getDisplayName(),applicationContext.getApplicationName(),busProperties.getId()};
        MessageRemoteApplicationEvent event
                = new MessageRemoteApplicationEvent(message, busProperties.getId(), "spring-cloud-bus-consumer:0");

        applicationContext.publishEvent(event);

        return ResponseEntity.ok(StringUtils.join(names,","
        ));

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
