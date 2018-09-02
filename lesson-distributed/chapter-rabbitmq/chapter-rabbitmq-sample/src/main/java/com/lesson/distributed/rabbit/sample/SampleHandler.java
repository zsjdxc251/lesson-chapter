package com.lesson.distributed.rabbit.sample;


import com.rabbitmq.client.Channel;

/**
 *
 */
public interface SampleHandler {


    /**
     * 执行方法
     *
     * @param channel
     */
    void execute(Channel channel) throws Exception;
}
