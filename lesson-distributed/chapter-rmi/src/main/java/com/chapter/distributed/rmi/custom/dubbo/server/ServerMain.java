package com.chapter.distributed.rmi.custom.dubbo.server;

import com.chapter.distributed.rmi.custom.server.CustomRpcService;
import com.chapter.distributed.rmi.server.impl.UserInfoService;
import com.chapter.distributed.rmi.service.ICustomRpcService;
import com.chapter.distributed.rmi.service.IUserInfoService;

/**
 * @author zhengshijun
 * @version created on 2018/6/28.
 */
public class ServerMain {


    public static void main(String[] args){


        ICustomRpcService userInfoService = new CustomRpcService();
        ServiceConfig<ICustomRpcService> serviceServiceConfig = new ServiceConfig<>(userInfoService,ICustomRpcService.class);



        serviceServiceConfig.doExportUrlsFor1Protocol();
    }
}
