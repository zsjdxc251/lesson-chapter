package com.lesson.microservice.cloud.stream.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhengshijun
 * @version created on 2018/12/22.
 */
public interface ChannalManager {


    @Output("channel_name_arg0")
    MessageChannel outputChannel();

    @Input("channel_name_arg1")
    SubscribableChannel inputChannel1();

    @Input("channel_name_arg2")
    SubscribableChannel inputChannel2();


}


