package com.lesson.microservice.boot.actuator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 *  {@link LoggersEndpoint} logger 端点 可以通过此端点修改运行时日志级别
 * @author zhengshijun
 * @version created on 2018/12/23.
 */
@RestController
@RequestMapping("/log")
public class LoggerController {
    
    private static final Logger log = LoggerFactory.getLogger(LoggerController.class);


    @GetMapping("/level")
    public ResponseEntity<String> logLevel(){

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("isDebugEnabled").append(" : ").append(log.isDebugEnabled());
        stringBuffer.append("\n");
        stringBuffer.append("isInfoEnabled").append(" : ").append(log.isInfoEnabled());

        return ResponseEntity.ok(stringBuffer.toString());
    }

}
