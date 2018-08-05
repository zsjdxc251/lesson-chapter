package com.chapter.microservice.boot.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfoService {

    
    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);


    public UserInfoService(){
        log.info("UserInfoService init");
    }
}
