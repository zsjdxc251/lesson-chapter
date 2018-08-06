package com.chapter.microservice.cloud.zookeeper;


import com.chapter.microservice.cloud.zookeeper.anno.CustomLoadBalanced;
import com.chapter.microservice.cloud.zookeeper.interceptor.CustomClientHttpRequestInterceptor;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.util.Collection;


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
     * @param
     * @return
     */
//    @Bean
//    @Autowired
//    public RestTemplate customInterceptorRestTemplate(@Nonnull @Qualifier("customClientHttpRequestInterceptor")
//                                                       ClientHttpRequestInterceptor clientHttpRequestInterceptor){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(ImmutableList.of(clientHttpRequestInterceptor));
//        return restTemplate;
//    }


    @CustomLoadBalanced
    @Bean
    public RestTemplate customInterceptorRestTemplate(){

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public Object customInterceptor(@CustomLoadBalanced Collection<RestTemplate> restTemplates,ClientHttpRequestInterceptor clientHttpRequestInterceptor){
        if (!ObjectUtils.isEmpty(restTemplates)){
            restTemplates.forEach(restTemplate -> restTemplate.setInterceptors(ImmutableList.of(clientHttpRequestInterceptor)));
        }
        return new Object();

    }


    @Bean
    @Primary
    public ClientHttpRequestInterceptor customClientHttpRequestInterceptor(){

        return new CustomClientHttpRequestInterceptor();
    }


}
