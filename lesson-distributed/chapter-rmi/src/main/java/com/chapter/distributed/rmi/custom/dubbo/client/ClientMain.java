package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.service.ICustomRpcService;

import java.util.stream.IntStream;

/**
 * @author zhengshijun
 * @version created on 2018/6/28.
 */
public class ClientMain {

    public static void main(String[] args){


        ReferenceConfig<ICustomRpcService> referenceConfig = new ReferenceConfig<>(ICustomRpcService.class);

        ICustomRpcService customRpcService = referenceConfig.get();


        IntStream.range(1,30).forEach(i->{

            new Thread(()->{
                System.out.println(customRpcService.getName("33=="+i));
            }).start();
        });

    }
}
