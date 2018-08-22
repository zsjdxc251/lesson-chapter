package com.lesson.microservice.boot.sample.microservice.cloud.integral.hystrix.controller;


import com.lesson.microservice.boot.sample.microservice.cloud.integral.api.entity.Order;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private static final Random RANDOM = new Random();

    @Autowired
    private ApplicationContext applicationContext;


    @HystrixCommand(fallbackMethod = "errorContent" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "100")
    })
    @GetMapping("/builder")
    public ResponseEntity<Order> builderOrder(Order order) throws InterruptedException{

        log.info("order:{}",order);
        if (order == null){
            order = new Order();
        }

        int sleepTime = RANDOM.nextInt(200);

        log.info("休眠：{}",sleepTime);
        TimeUnit.MILLISECONDS.sleep(sleepTime);


        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(new Date());
        order.setOrderNo(applicationContext.getId());


        return ResponseEntity.ok(order);
    }

    public ResponseEntity<Order> errorContent(Order order){
        if (order == null){
            order = new Order();
        }
        order.setId("errorContent");
        return ResponseEntity.ok(order);
    }


}
