package com.chapter.microservice.cloud.eureka.consumer.controller;

import com.chapter.cloud.eureka.api.model.UserInfo;
import com.chapter.cloud.eureka.api.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengshijun
 * @version created on 2018/8/3.
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/create")
    public ResponseEntity<UserInfo> create(String userName){

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo = userInfoService.create(userInfo);
        return ResponseEntity.ok(userInfo);
    }
}
