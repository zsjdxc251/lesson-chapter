package com.lesson.microservice.boot.sample.microservice.cloud.stream.sample.controller;


import com.lesson.microservice.boot.sample.microservice.cloud.stream.sample.source.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(String message){

        boolean result = messageSource.outputChannel().send(new GenericMessage<>(message));


        return result? ResponseEntity.ok("发送成功"):ResponseEntity.noContent().build();

    }
    @GetMapping("/kafka/send")
    public ResponseEntity<String> sendKafkaMessage(String message){

        boolean result = messageSource.kafkaOutputChannel().send(new GenericMessage<>(message));


        return result? ResponseEntity.ok("发送成功"):ResponseEntity.noContent().build();

    }
}
