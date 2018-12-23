package com.lesson.microservice.cloud.stream.sample.source;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface MessageSource{
    @Output("channel_name_arg0")
    MessageChannel outputChannel();

    @Input("channel_name_arg1")
    SubscribableChannel inputChannel();

    @Output("channel_name_arg2")
    MessageChannel kafkaOutputChannel();
}
