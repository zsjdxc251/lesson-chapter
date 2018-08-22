package com.lesson.microservice.boot.sample.microservice.cloud.stream.sample.source;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface MessageSource{
    @Output("channel_name_arg0")
    MessageChannel outputChannel();

    @Input("channel_name_arg1")
    SubscribableChannel inputChannel();
}
