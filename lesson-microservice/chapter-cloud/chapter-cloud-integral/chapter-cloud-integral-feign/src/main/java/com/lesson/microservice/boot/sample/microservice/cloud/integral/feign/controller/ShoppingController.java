package com.lesson.microservice.boot.sample.microservice.cloud.integral.feign.controller;


import com.lesson.microservice.boot.sample.microservice.cloud.integral.api.service.IOrderService;
import com.lesson.microservice.boot.sample.microservice.cloud.integral.api.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShoppingController {
    
    private static final Logger log = LoggerFactory.getLogger(ShoppingController.class);

    @Autowired
    private IOrderService orderService;


    @GetMapping("/builder")
    public ResponseEntity<Order> builderShop(Integer number){

        Order order = new Order();
        order.setNumber(number);
        order = orderService.builderOrder(order);
        return ResponseEntity.ok(order);
    }
}
