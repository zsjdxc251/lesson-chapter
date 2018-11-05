package com.lesson.distributed.netty.quickstart.bio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
public class Server {

    private static ServerSocket serverSocket;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2,10,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().build());

    public static void start(int port)  {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                executor.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }


    public static void main(String[] args){
        start(8080);

    }


}
