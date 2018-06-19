package com.chapter.distributed.rmi.custom.client;

import com.chapter.distributed.rmi.custom.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;

    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest(method.getDeclaringClass().getName(),method.getName(),args);
        TCPTransport tcpTransport = new TCPTransport(host,port);
        return tcpTransport.send(rpcRequest);
    }
}
