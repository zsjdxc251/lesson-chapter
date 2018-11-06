package com.lesson.distributed.netty.quickstart.aio;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;

/**
 * @author zhengshijun
 * @version created on 2018/11/6.
 */
@Slf4j
public class AioClient {

    public static class TcpEchoClient{

        private InetSocketAddress inetSocketAddress;

        private AsynchronousSocketChannel client;

        public TcpEchoClient(InetSocketAddress inetSocketAddress) {
            this.inetSocketAddress = inetSocketAddress;


        }



        public void start(){
           try {
               client = AsynchronousSocketChannel.open();
               client.connect(inetSocketAddress, null, new CompletionHandler<Void, Void>() {
                   @Override
                   public void completed(Void result, Void attachment) {
                       log.info("connect completed");

                   }

                   @Override
                   public void failed(Throwable exc, Void attachment) {
                       log.info("failed");
                   }
               });

               ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
               client.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {

                   @Override
                   public void completed(Integer result, Object attachment) {
                       log.info("read completed");
                   }

                   @Override
                   public void failed(Throwable exc, Object attachment) {
                       log.info("failed");
                   }
               });


           } catch (IOException e) {
               log.error(StringUtils.EMPTY,e);
           }
        }

        public void writeMessage(String message){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(message.getBytes());
            byteBuffer.flip();
            client.write(byteBuffer);

        }
    }

    public static void main(String[] args){

        TcpEchoClient tcpEchoClient = new TcpEchoClient(new InetSocketAddress("127.0.0.1",8080));
        tcpEchoClient.start();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            tcpEchoClient.writeMessage(scanner.nextLine());
        }

    }
}
