package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.service.ICustomRpcService;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public class Consumer {

    public static void main(String[] args){


        ICustomRpcService customRpcService = DubboConsumerProxy.clientProxy(ICustomRpcService.class,"",8080);
        System.out.println(customRpcService.getName(""));

    }
}
