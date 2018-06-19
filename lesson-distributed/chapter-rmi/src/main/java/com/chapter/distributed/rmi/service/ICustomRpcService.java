package com.chapter.distributed.rmi.service;

import com.chapter.distributed.rmi.model.User;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public interface ICustomRpcService {

    String getName(String lastName);


    User invoke(User user);
}
