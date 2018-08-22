package com.lesson.microservice.boot.sample.microservice.cloud.bus.api;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MessageRemoteApplicationEvent extends RemoteApplicationEvent {


    public MessageRemoteApplicationEvent(String message, String originService,
                                         String destinationService) {
        super(message, originService, destinationService);
    }

    public String getMessage() {
        return (String) getSource();
    }

}
