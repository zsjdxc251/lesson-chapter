package com.lesson.microservice.cloud.stream.rabbit.controller;

import com.lesson.microservice.cloud.stream.rabbit.ChannalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengshijun
 * @version created on 2018/12/22.
 */
@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private ChannalManager manager;


    @GetMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(String msg){

        manager.outputChannel().send(new GenericMessage<>(msg));
        return ResponseEntity.ok(Thread.currentThread().getName());
    }
}
