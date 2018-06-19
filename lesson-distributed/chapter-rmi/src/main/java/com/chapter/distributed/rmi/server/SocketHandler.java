package com.chapter.distributed.rmi.server;



import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class SocketHandler {

    public static void main(String[] args) throws Exception{


        ServerSocket serverSocket = new ServerSocket(1099);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int len =0;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes,0,len);
        }

        System.out.println(new String(byteArrayOutputStream.toByteArray()));



    }
}
