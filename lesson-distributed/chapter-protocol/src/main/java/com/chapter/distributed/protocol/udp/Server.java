package com.chapter.distributed.protocol.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author zhengshijun
 * @version created on 2018/6/20.
 */
public class Server {
    public static void main(String[] args) throws Exception{
         //创建服务, 并且接收一个数据包
        DatagramSocket datagramSocket=new DatagramSocket(8080);
        byte[] receiveData=new byte[1024];
        DatagramPacket receivePacket=
                new DatagramPacket(receiveData,receiveData.length);
        datagramSocket.receive(receivePacket);;

        System.out.println(new String
                (receiveData,0,receivePacket.getLength()));

    }
}
