package com.chapter.distributed.protocol;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhengshijun
 * @version created on 2018/6/20.
 */
public class App {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes,0,len);
                bufferedOutputStream.flush();
            }

            bytes = byteArrayOutputStream.toByteArray();

            inputStream.close();
            byteArrayOutputStream.close();
            byteArrayOutputStream.close();
            System.out.println(new String(bytes));
        }

    }
}
