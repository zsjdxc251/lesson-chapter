package com.chapter.microservice.cloud.config.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhengshijun
 * @version created on 2018/8/1.
 */
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Value("${login.username}")
    private String loginUsername;

    @GetMapping("/loginName")
    public ResponseEntity<String> loginName(HttpServletRequest request){
        UrlPathHelper urlPathHelper = new UrlPathHelper();

        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        log.info("lookupPath:{}",lookupPath);
        return ResponseEntity.ok(loginUsername);
    }
}
