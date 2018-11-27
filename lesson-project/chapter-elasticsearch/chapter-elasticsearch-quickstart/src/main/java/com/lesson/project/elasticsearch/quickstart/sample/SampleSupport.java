package com.lesson.project.elasticsearch.quickstart.sample;

import org.elasticsearch.client.transport.TransportClient;

/**
 * @author zhengshijun
 * @version created on 2018/11/27.
 */
public interface SampleSupport {

    /**
     *
     *   演示例子执行方法
     *
     * @param client
     */
    void request(TransportClient client);
}
