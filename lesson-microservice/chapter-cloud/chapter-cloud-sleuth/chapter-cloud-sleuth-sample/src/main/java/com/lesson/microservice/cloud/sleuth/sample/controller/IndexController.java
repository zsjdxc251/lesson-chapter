package com.lesson.microservice.cloud.sleuth.sample.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("/home")
    public ResponseEntity<String> home(String message){

        log.info("message:{}",message);


        return ResponseEntity.ok("result");
    }

}
