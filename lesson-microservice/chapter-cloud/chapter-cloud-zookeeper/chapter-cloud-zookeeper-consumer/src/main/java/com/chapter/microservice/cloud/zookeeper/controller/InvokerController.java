package com.chapter.microservice.cloud.zookeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/custom/users")
    public ResponseEntity<String> customUsers(){

        return ResponseEntity.ok("");
    }

    @GetMapping("/load/users")
    public ResponseEntity<String> loadBalancedUsers(){

        return ResponseEntity.ok("");
    }
}
