package com.chapter.microservice.cloud.feign.client.service;

import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "spring-cloud-zookeeper-provider")
public interface InvokerService {

    @GetMapping("/invoker/users")
    String customUsers();

    @PostMapping("/invoker/create")
    UserInfo customCreate(UserInfo userInfo);

}
