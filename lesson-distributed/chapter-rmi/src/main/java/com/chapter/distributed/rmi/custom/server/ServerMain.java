package com.chapter.distributed.rmi.custom.server;

import com.chapter.distributed.rmi.service.ICustomRpcService;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class ServerMain {

    public static void main(String[] args){


        CustomRpcService customRpcService = new CustomRpcService();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(customRpcService,8080);

    }
}
