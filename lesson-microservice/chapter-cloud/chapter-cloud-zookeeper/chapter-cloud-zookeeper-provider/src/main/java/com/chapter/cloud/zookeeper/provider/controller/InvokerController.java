package com.chapter.cloud.zookeeper.provider.controller;


import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoker")
public class InvokerController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/users")
    public ResponseEntity<String> customUsers(String name,Integer age){

        return ResponseEntity.ok("This is port "+serverPort+" name:"+name+" age:"+age);
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfo> customCreate(@RequestBody UserInfo userInfo){

        userInfo.setUserName("serverPort:"+serverPort);
        return ResponseEntity.ok(userInfo);
    }
}
