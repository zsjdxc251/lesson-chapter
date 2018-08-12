package com.chapter.microservice.cloud.zookeeper.api.service;

import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;


public interface IUserInfoService {

    UserInfo customCreate(UserInfo userInfo);
}
