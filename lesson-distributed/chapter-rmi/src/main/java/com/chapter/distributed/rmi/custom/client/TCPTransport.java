package com.chapter.distributed.rmi.custom.client;

import com.chapter.distributed.rmi.custom.RpcRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class TCPTransport {

    private static final Logger log = LoggerFactory.getLogger(TCPTransport.class);


    private String host;

    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket(){

        log.info("创建连接");
        Socket socket = null;
        try {
            socket = new Socket(host,port);
        } catch (IOException e) {
            log.error("创建连接失败",e);
        }
        return socket;
    }


    public Object send(RpcRequest rpcRequest) {
        Socket socket = newSocket();

        log.info("rpcRequest:{}",rpcRequest);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Object result = inputStream.readObject();
            outputStream.close();
            inputStream.close();
            return result;
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);
        }
        return null;
    }



}
