package com.chapter.distributed.rmi.custom.dubbo.server;

import com.chapter.distributed.rmi.custom.dubbo.ExchangeHandler;
import com.chapter.distributed.rmi.custom.dubbo.Request;
import com.chapter.distributed.rmi.custom.dubbo.Response;
import com.chapter.distributed.rmi.custom.dubbo.client.Invocation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable{


    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,300,2000, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(200));


    private ServerSocket serverSocket;

    private ExchangeHandler exchangeHandler;

    public Server(ExchangeHandler exchangeHandler){
        this.exchangeHandler = exchangeHandler;

    }

    public void start(){
        try {
            serverSocket = new ServerSocket(20880);
            threadPoolExecutor.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run() {

        Socket socket = null;
         for (;;) {
             try {
                 socket =  serverSocket.accept();


                 ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                 Request request = (Request)objectInputStream.readObject();


                 Object object = exchangeHandler.reply(request.getData());



//                 Response response = new Response();
//                 response.setResult(object);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                 objectOutputStream.writeObject(object);
             } catch (Exception e){
                 e.printStackTrace();
             }


         }

    }
}
