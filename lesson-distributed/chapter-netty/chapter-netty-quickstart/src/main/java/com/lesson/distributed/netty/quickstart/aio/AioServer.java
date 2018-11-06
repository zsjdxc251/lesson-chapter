package com.lesson.distributed.netty.quickstart.aio;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/11/6.
 */
@Slf4j
public class AioServer {


    public static class TcpEchoServer{
        private InetSocketAddress inetSocketAddress;

        public TcpEchoServer(int port) {
            this.inetSocketAddress = new InetSocketAddress(port);
        }

        public void listener(){

            try {

                AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
                serverSocketChannel.bind(inetSocketAddress);

                serverSocketChannel.accept(null,new CompletionHandler<AsynchronousSocketChannel,Object>(){

                    @Override
                    public void completed(AsynchronousSocketChannel result, Object attachment) {
                        log.info("serverSocketChannel accept completed");


                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        result.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, Object>() {

                            @Override
                            public void completed(Integer result, Object attachment) {
                                log.info("server read completed");
                                log.info("byteBuffer:{}",new String(byteBuffer.array()));
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {

                            }
                        });

                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        log.info("server failed");
                    }
                });




                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (IOException e) {
                log.error(StringUtils.EMPTY,e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }




    }


    public static void main(String[] args){

        new TcpEchoServer(8080).listener();

    }
}
