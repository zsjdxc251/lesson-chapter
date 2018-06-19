package com.chapter.distributed.rmi.custom.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class RpcServer{

    private static final Logger log = LoggerFactory.getLogger(RpcServer.class);

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public void publisher(Object service,int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                executor.execute(new ProcessorHandler(socket,service));
            }
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);

        } finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (Exception e) {
                    log.error(StringUtils.EMPTY,e);
                }
            }
        }



    }

}
