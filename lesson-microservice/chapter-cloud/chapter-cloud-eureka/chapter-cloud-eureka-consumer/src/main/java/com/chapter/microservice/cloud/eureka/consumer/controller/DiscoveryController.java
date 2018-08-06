package com.chapter.microservice.cloud.eureka.consumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/getServices")
    public ResponseEntity<List<String>> getServices(){
        return ResponseEntity.ok(discoveryClient.getServices());
    }


    @GetMapping(value = "/getInstances/{serviceId}",produces={"application/json;charset=UTF-8"
    })
    public ResponseEntity<List<ServiceInstance>> getInstances(@PathVariable("serviceId")
                                                                          String serviceId){


        return ResponseEntity.ok(discoveryClient.getInstances(serviceId));
    }



}
