package com.chapter.distributed.dubbo.provider.impl;

import com.chapter.distributed.dubbo.service.IUserInfoService;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class CustomUserInfoService implements IUserInfoService {

    @Override
    public String getName(String lastName) {
        return "Custom i am " + lastName;
    }


    @Override
    public String getAge(int age) {
        return null;
    }
}
