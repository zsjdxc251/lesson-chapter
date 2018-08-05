package com.chapter.cloud.zookeeper.provider.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoker")
public class InvokerController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/users")
    public ResponseEntity<String> customUsers(){

        return ResponseEntity.ok(serverPort);
    }
}
