package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.service.ICustomRpcService;

/**
 * @author zhengshijun
 * @version created on 2018/6/28.
 */
public class ClientMain {

    public static void main(String[] args){


        ReferenceConfig<ICustomRpcService> referenceConfig = new ReferenceConfig<>(ICustomRpcService.class);

        ICustomRpcService customRpcService = referenceConfig.get();


        customRpcService.getName("33");

    }
}
