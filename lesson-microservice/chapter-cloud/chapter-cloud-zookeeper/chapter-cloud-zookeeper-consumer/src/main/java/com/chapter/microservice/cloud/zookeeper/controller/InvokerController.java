package com.chapter.microservice.cloud.zookeeper.controller;

import com.chapter.microservice.cloud.zookeeper.anno.CustomLoadBalanced;
import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/invoker")
public class InvokerController {



    @Autowired
    @LoadBalanced
    private RestTemplate loadBalancedRestTemplate;

    @Autowired
    @CustomLoadBalanced
    //@Qualifier("customInterceptorRestTemplate")
    private RestTemplate customInterceptorRestTemplate;


    @GetMapping("/custom/users/{serviceId}")
    public ResponseEntity<String> customUsers(@PathVariable("serviceId") String serviceId){


        return customInterceptorRestTemplate.getForEntity("http://".concat(serviceId)+"/invoker/users",String.class,ImmutableMap.of("33","33"));
    }

    @PostMapping("/custom/create/{serviceId}")
    public ResponseEntity<UserInfo> customCreate(@PathVariable("serviceId") String serviceId, @RequestBody UserInfo userInfo){
        return customInterceptorRestTemplate.postForEntity("http://".concat(serviceId)+"/invoker/create",userInfo,UserInfo.class);
    }

    @GetMapping("/load/users/{serviceId}")
    public ResponseEntity<String> loadBalancedUsers(@PathVariable("serviceId") String serviceId){

        return loadBalancedRestTemplate.getForEntity("http://".concat(serviceId)+"/invoker/users",String.class);
    }
}


