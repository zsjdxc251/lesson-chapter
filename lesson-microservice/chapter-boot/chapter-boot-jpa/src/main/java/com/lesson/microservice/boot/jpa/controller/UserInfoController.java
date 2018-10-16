package com.lesson.microservice.boot.jpa.controller;


import com.lesson.microservice.boot.jpa.entity.UserInfo;
import com.lesson.microservice.boot.jpa.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

/**
 *
 * @author  zhengshijun
 *
 *
 */

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping("/create")
    public ResponseEntity<UserInfo> create(){

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername("zsjdxc25"+System.currentTimeMillis());

        userInfo.setAge(new Random().nextInt());
        userInfo = userInfoRepository.save(userInfo);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<UserInfo> getUserInfo(){
        Optional<UserInfo> optional = userInfoRepository.findById(16L);
        return ResponseEntity.ok(optional.isPresent()?optional.get():new UserInfo());
    }
}
