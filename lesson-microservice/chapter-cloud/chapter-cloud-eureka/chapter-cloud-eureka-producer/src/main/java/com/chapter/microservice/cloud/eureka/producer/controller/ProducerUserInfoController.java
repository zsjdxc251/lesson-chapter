package com.chapter.microservice.cloud.eureka.producer.controller;

import com.chapter.cloud.eureka.api.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengshijun
 * @version created on 2018/8/3.
 */
@RestController
@RequestMapping("/user")
public class ProducerUserInfoController {

    private static final Logger log = LoggerFactory.getLogger(ProducerUserInfoController.class);

    @PostMapping("/create")
    public ResponseEntity<UserInfo> create(@RequestBody UserInfo userInfo){
        log.info("create:{}",userInfo);
        return ResponseEntity.ok(userInfo);
    }
}
