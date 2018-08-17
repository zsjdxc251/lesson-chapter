package com.chapter.microservice.cloud.feign.custom.controller;

import com.chapter.microservice.cloud.feign.custom.service.InvokerService;
import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoker")
public class InvokerController {

    @Autowired
    private InvokerService invokerService;


    @GetMapping("/users")
    public ResponseEntity<String> customUsers(String nameMessage){

        return ResponseEntity.ok(invokerService.customUsers(nameMessage));
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfo> customCreate(@RequestBody UserInfo userInfo){


        return ResponseEntity.ok(invokerService.customCreate(userInfo));
    }
}
