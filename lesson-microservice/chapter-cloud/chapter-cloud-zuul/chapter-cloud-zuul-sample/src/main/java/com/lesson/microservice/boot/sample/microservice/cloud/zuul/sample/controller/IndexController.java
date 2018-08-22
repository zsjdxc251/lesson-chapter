package com.lesson.microservice.boot.sample.microservice.cloud.zuul.sample.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/get")
    public String get(){
        return "zsjdxc251";
    }
}
