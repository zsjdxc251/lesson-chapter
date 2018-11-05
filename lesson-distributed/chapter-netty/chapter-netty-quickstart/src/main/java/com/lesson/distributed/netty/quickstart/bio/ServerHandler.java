package com.lesson.distributed.netty.quickstart.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
@Slf4j
public class ServerHandler implements Runnable{

    private final Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        log.info("in server handler");
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printWriter = new PrintWriter(socket.getOutputStream(),true);
            String message;
            while (true) {

                message = bufferedReader.readLine();

                System.out.println("server:"+message);

                printWriter.println("handler:"+message);

            }

        } catch (SocketException e) {
            log.warn("socket out");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            log.info("socket close");
            if (printWriter != null){
                printWriter.close();
            }

            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
