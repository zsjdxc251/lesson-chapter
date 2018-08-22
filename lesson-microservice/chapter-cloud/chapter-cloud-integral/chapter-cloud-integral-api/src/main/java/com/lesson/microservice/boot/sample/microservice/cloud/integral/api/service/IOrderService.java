package com.lesson.microservice.boot.sample.microservice.cloud.integral.api.service;

import com.lesson.microservice.boot.sample.microservice.cloud.integral.api.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("spring-cloud-integral-hystrix")
public interface IOrderService {

    @GetMapping("/order/builder")
    Order builderOrder(@RequestParam("order") Order order);
}
