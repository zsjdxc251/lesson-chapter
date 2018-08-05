package com.chapter.microservice.cloud.zookeeper.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author zhengshijun
 */
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        return null;
    }
}
