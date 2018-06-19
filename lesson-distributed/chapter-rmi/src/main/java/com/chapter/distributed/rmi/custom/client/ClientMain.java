package com.chapter.distributed.rmi.custom.client;

import com.chapter.distributed.rmi.model.User;
import com.chapter.distributed.rmi.service.ICustomRpcService;
import com.chapter.distributed.rmi.service.IUserInfoService;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class ClientMain {

    public static void main(String[] args){


        ICustomRpcService customRpcService = RpcClientProxy.clientProxy(ICustomRpcService.class,"127.0.0.1",8080);


        System.out.println(customRpcService.getName("zsj"));

        User user = new User();
        System.out.println(customRpcService.invoke(user));



    }
}
