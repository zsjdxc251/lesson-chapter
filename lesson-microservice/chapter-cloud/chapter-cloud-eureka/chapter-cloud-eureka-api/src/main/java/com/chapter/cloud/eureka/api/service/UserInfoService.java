package com.chapter.cloud.eureka.api.service;

import com.chapter.cloud.eureka.api.model.UserInfo;

/**
 * @author zhengshijun
 * @version created on 2018/8/3.
 */
public interface UserInfoService {

    /**
     *  创建用户
     * @param userInfo
     * @return
     */
    UserInfo create(UserInfo userInfo);
}
