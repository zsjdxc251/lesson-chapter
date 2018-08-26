package com.lesson.microservice.cloud.sleuth.api.service;


import com.lesson.microservice.cloud.sleuth.api.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "spring-cloud-sleuth-hystrix")
public interface UserInfoService {


    @PostMapping("/user/create")
    UserInfo create(@RequestBody UserInfo userInfo);
}
