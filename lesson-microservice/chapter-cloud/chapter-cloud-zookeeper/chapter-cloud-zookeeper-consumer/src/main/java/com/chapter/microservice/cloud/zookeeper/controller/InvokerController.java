package com.chapter.microservice.cloud.zookeeper.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/invoker")
public class InvokerController {



    @Autowired
    @LoadBalanced
    private RestTemplate loadBalancedRestTemplate;

    @Autowired
    @Qualifier("customInterceptorRestTemplate")
    private RestTemplate customInterceptorRestTemplate;


    @GetMapping("/custom/users/{serviceId}")
    public ResponseEntity<String> customUsers(@PathVariable("serviceId") String serviceId){


        return customInterceptorRestTemplate.getForEntity("http://".concat(serviceId)+"/invoker/users",String.class,ImmutableMap.of("33","33"));
    }

    @GetMapping("/load/users/{serviceId}")
    public ResponseEntity<String> loadBalancedUsers(@PathVariable("serviceId") String serviceId){

        return loadBalancedRestTemplate.getForEntity("http://".concat(serviceId)+"/invoker/users",String.class);
    }
}
