package com.chapter.distributed.dubbo;

import com.alibaba.dubbo.common.utils.NetUtils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
public class App {

    public static void main(String[] args) throws Exception{
//        System.out.println(InetAddress.getLocalHost().getHostAddress());;
//
//        System.out.println( NetUtils.getLocalHost());

        Socket socket = new Socket();
        try {
            SocketAddress addr = new InetSocketAddress("192.168.1.251", 8080);
            socket.connect(addr, 1000);
            String host = socket.getLocalAddress().getHostAddress();
            System.out.println(host);
        } finally {
            try {
                socket.close();
            } catch (Throwable e) {
            }
        }
    }
}
