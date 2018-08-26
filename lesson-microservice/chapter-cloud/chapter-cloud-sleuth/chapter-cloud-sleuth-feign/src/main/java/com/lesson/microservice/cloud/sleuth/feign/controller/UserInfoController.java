package com.lesson.microservice.cloud.sleuth.feign.controller;


import com.lesson.microservice.cloud.sleuth.api.entity.UserInfo;
import com.lesson.microservice.cloud.sleuth.api.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/create")
    public ResponseEntity<UserInfo> create(String username){

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo = userInfoService.create(userInfo);
       return ResponseEntity.ok(userInfo);
    }
}
