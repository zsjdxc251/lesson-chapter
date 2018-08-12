package com.chapter.microservice.cloud.feign.client.impl;

import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;
import com.chapter.microservice.cloud.zookeeper.api.service.IUserInfoService;

public class UserInfoService implements IUserInfoService {

    @Override
    public UserInfo customCreate(UserInfo userInfo) {
        return null;
    }
}
