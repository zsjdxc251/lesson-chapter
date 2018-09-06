package com.chapter.distributed.dubbo.consumer.impl;

import com.chapter.distributed.dubbo.service.IUserInfoService;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
public class ErrorUserInfoService implements IUserInfoService {

    @Override
    public String getName(String lastName) {
        return "出错了";
    }

    @Override
    public String getAge(int age) {
        return null;
    }
}
