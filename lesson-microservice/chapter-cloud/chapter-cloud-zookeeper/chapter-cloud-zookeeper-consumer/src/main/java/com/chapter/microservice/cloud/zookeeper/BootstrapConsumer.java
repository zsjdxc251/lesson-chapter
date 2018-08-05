package com.chapter.microservice.cloud.zookeeper;


import com.chapter.microservice.cloud.zookeeper.interceptor.CustomClientHttpRequestInterceptor;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nonnull;


@SpringBootApplication
@EnableDiscoveryClient
public class BootstrapConsumer
{
    public static void main( String[] args )
    {
        SpringApplication.run(BootstrapConsumer.class,args);
    }


    @LoadBalanced
    @Bean
    public RestTemplate loadBalancedRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;

    }

    /**
     * 可以类似 @LoadBalanced 通过 @Qualifier 自定义
     * @param clientHttpRequestInterceptor
     * @return
     */
    @Bean
    @Autowired
    public RestTemplate customInterceptorRestTemplate(@Nonnull @Qualifier("customClientHttpRequestInterceptor")
                                                       ClientHttpRequestInterceptor clientHttpRequestInterceptor){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(ImmutableList.of(clientHttpRequestInterceptor));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestInterceptor customClientHttpRequestInterceptor(){

        return new CustomClientHttpRequestInterceptor();
    }
}
