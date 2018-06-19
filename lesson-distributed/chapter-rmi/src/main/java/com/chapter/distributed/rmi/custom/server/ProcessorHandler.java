package com.chapter.distributed.rmi.custom.server;

import com.chapter.distributed.rmi.custom.RpcRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class ProcessorHandler implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(ProcessorHandler.class);

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            log.info("rpcRequest:{}",rpcRequest);
            Object result = invoke(rpcRequest);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(result);
        } catch (Exception e) {
            log.error(StringUtils.EMPTY ,e);

        }
    }

    private Object invoke(RpcRequest rpcRequest) throws NoSuchMethodException,InvocationTargetException, IllegalAccessException{
        Object[] parametersValue = rpcRequest.getParameters();
        Class<?> [] parametersClass = new Class[parametersValue.length];
        for (int i=0;i<parametersValue.length;i++){
            parametersClass[i] = parametersValue[i].getClass();
        }

        Method method = service.getClass().getMethod(rpcRequest.getMethodName(),parametersClass);
        return method.invoke(service,parametersValue);
    }
}
