package com.lesson.distributed.netty.quickstart.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
public class Client {


    public static void main(String[] args){


       try {

           Socket socket = new Socket("127.0.0.1",49583);


           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);


           for (;;) {
               Scanner scanner = new Scanner(System.in);
               String message = scanner.nextLine();
               printWriter.println(message);


               String readMessage = bufferedReader.readLine();
               System.out.println(readMessage);
           }


       } catch (Exception e) {
           e.printStackTrace();
       }


    }
}
