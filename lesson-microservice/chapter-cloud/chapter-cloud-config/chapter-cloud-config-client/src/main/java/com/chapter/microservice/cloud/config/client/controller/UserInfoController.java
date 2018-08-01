package com.chapter.microservice.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengshijun
 * @version created on 2018/8/1.
 */
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserInfoController {

    @Value("${login.username}")
    private String loginUsername;

    @GetMapping("/loginName")
    public ResponseEntity<String> loginName(){
        return ResponseEntity.ok(loginUsername);
    }
}
