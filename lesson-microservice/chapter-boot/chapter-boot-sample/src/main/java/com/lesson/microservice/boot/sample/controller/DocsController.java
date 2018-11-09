package com.lesson.microservice.boot.sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengshijun
 * @version created on 2018/11/9.
 */
@RestController
@RequestMapping("/docs")
public class DocsController {

    @GetMapping("/users")
    public ResponseEntity<String> users(){

        return ResponseEntity.ok("success");
    }
}
