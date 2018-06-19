package com.chapter.distributed.rmi.custom.server;

import com.chapter.distributed.rmi.model.User;
import com.chapter.distributed.rmi.service.ICustomRpcService;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class CustomRpcService implements ICustomRpcService {
    @Override
    public String getName(String lastName) {


        return "custom i am " +lastName ;
    }

    @Override
    public User invoke(User user) {
        user.setAge(user.getAge()+2);
        return user;
    }
}
