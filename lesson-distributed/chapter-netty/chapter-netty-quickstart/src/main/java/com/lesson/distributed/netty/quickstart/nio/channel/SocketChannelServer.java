package com.lesson.distributed.netty.quickstart.nio.channel;

import com.lesson.distributed.netty.quickstart.nio.buffer.Buffers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
@Slf4j
public class SocketChannelServer {

    private static class TcpEchoServer implements Runnable {

        private InetSocketAddress inetSocketAddress;

        private Charset charset = Charset.forName("UTF-8");

        private ServerSocketChannel serverSocketChannel = null;

        private Selector selector;

        public TcpEchoServer(int port) throws IOException {
            inetSocketAddress = new InetSocketAddress(port);

            // 创建服务通道
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);


            // 设置绑定服务器端口 设置最大的连接缓存数为100 客户端大小
            serverSocketChannel.bind(inetSocketAddress,100);


            // 创建选择器
            selector = Selector.open();
            // 注册 tcp
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            log.info("start success");
        }

        @Override
        public void run() {


            while (!Thread.currentThread().isInterrupted()) {
                SelectionKey selectionKey = null;
                try {
                    int select = selector.select();
                    if (select == 0){
                        continue;
                    }

                    Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

                    while (selectionKeys.hasNext()) {
                        selectionKey = selectionKeys.next();
                        selectionKeys.remove();



                        // 连接事件
                        if (selectionKey.isAcceptable()){
                            log.info("连接");
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);

                            // 向选择器注册这个通道和普通通道感兴趣的事件，同时提供这个新通道相关的缓冲区
                            socketChannel.register(selector,SelectionKey.OP_READ,new Buffers(256, 256));

                        }

                        // 读事件
                        if (selectionKey.isReadable()){
                            log.info("读取");

                            Buffers buffers = (Buffers)selectionKey.attachment();

                            ByteBuffer readBuffer = buffers.getReadBuffer();

                            ByteBuffer writeBuffer = buffers.getWriteBuffer();

                            // 获取对应通道
                            SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                            // 从底层socket 读入缓存区中
                            socketChannel.read(readBuffer);
                            readBuffer.flip();

                            // 解码客户端发送过来的数据
                            CharBuffer charBuffer = charset.decode(readBuffer);

                            log.info("服务端收到客户端数据：{}",charBuffer.array());

                            readBuffer.rewind();


                            // 向客户端发送数据
                            writeBuffer.put("echo from service:".getBytes(StandardCharsets.UTF_8));
                            writeBuffer.put(readBuffer);

                            readBuffer.clear();

                            // 设置通道写事件
                            selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);

                        }

                        // 写事件
                        if (selectionKey.isWritable()){
                            log.info("写入");
                            Buffers buffers = (Buffers)selectionKey.attachment();

                            ByteBuffer writeBuffer = buffers.getWriteBuffer();

                            writeBuffer.flip();
                            log.info("写入数据长度：{}",writeBuffer.limit());

                            // 获取对应通道
                            SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                            int len = 0;
                            while (writeBuffer.hasRemaining()) {
                                len = socketChannel.write(writeBuffer);
                                // 底层缓冲区已满
                                if (len == 0){
                                    break;
                                }
                            }

                            writeBuffer.compact();

                            if(len != 0){
                                /*取消通道的写事件*/
                                selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_WRITE));
                            }
                        }


                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                    if (selectionKey != null) {
                        selectionKey.cancel();
                        try {
                            selectionKey.channel().close();
                        } catch (IOException ex) {
                            log.error(StringUtils.EMPTY,ex);
                        }
                    }
                }
            }


        }
    }


    public static void main(String[] args) throws Exception{

        new Thread(new TcpEchoServer(8080)).start();

    }
}
