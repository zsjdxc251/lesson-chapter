package com.chapter.microservice.cloud.feign.custom.service;

import com.chapter.microservice.cloud.feign.custom.annotation.CustomFeignClient;
import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@CustomFeignClient(name = "spring-cloud-zookeeper-provider")
public interface InvokerService {

    @GetMapping("/invoker/users")
    String customUsers(String nameMessage);

    @PostMapping("/invoker/create")
    UserInfo customCreate(UserInfo userInfo);
}
