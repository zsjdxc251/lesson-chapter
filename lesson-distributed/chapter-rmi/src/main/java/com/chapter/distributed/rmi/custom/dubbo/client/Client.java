package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.custom.RpcRequest;
import com.chapter.distributed.rmi.custom.dubbo.Request;
import com.chapter.distributed.rmi.custom.dubbo.Response;
import com.chapter.distributed.rmi.custom.dubbo.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author zhengshijun
 * @version created on 2018/6/28.
 */
public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);
    private String host = "127.0.0.1";
    private int port = 20880;

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
    public Result request(Object request) {
        Socket socket = newSocket();
        try {
            Request req = new Request();
            req.setVersion("2.0.0");
            req.setTwoWay(true);
            req.setData(request);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(req);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Response result = (Response)inputStream.readObject();
            outputStream.close();
            inputStream.close();
            return new RpcResult(result.getResult());
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);
        }
        return null;
    }

}
