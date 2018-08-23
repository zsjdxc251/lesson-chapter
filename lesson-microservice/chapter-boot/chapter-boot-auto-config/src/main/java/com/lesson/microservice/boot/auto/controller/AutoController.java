package com.lesson.microservice.boot.auto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengshijun
 * @version created on 2018/8/23.
 */
@RestController
@RequestMapping("/auto")
public class AutoController {

    @GetMapping("/getName")
    public ResponseEntity<String> getName(){

        return ResponseEntity.ok("zsjdxc251");
    }
}
