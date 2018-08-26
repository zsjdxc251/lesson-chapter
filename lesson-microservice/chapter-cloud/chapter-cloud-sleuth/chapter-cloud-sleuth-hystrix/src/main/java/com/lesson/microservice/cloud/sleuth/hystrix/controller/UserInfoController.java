package com.lesson.microservice.cloud.sleuth.hystrix.controller;


import com.lesson.microservice.cloud.sleuth.api.entity.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Value("${server.port}")
    private String port;


    @PostMapping("/create")
    public ResponseEntity<UserInfo> create(@RequestBody UserInfo userInfo){

        if (userInfo != null){
            userInfo.setId(Long.parseLong(port));
            userInfo.setPassword("123456");
        }

        return ResponseEntity.ok(userInfo);
    }
}
