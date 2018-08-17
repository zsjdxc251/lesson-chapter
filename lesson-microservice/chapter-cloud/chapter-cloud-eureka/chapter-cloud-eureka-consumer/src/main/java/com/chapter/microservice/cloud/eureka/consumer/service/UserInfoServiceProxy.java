package com.chapter.microservice.cloud.eureka.consumer.service;

import com.chapter.cloud.eureka.api.model.UserInfo;
import com.chapter.cloud.eureka.api.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhengshijun
 * @version created on 2018/8/3.
 */
@Service
public class UserInfoServiceProxy implements UserInfoService {

    private static final String PROVIDER_SERVER_URL_PREFIX = "http://spring-cloud-netflix-eureka-producer";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserInfo create(UserInfo userInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserInfo> entity = new HttpEntity<>(userInfo,headers);

        ResponseEntity<UserInfo> responseEntity = restTemplate.postForEntity(PROVIDER_SERVER_URL_PREFIX+"/user/create",entity,UserInfo.class);

        return responseEntity.getBody();
    }
}
