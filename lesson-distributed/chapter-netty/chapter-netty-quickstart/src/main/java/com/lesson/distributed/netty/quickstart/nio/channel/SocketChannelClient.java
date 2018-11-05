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
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
@Slf4j
public class SocketChannelClient {

    public static class TcpEchoClient implements Runnable {

        private InetSocketAddress inetSocketAddress;
        private Charset charset = Charset.forName("UTF-8");
        private Random random = new Random();
        private SocketChannel socketChannel;
        private Selector selector;

        public TcpEchoClient(InetSocketAddress inetSocketAddress) throws IOException {
            this.inetSocketAddress = inetSocketAddress;

            // 创建tcp 通道
            socketChannel = SocketChannel.open();

            // 设置非阻塞
            socketChannel.configureBlocking(false);

            // 创建选择器
            selector = Selector.open();

            // 注册感兴趣事件
            socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, new Buffers(256, 256));

            // 连接服务端
            socketChannel.connect(inetSocketAddress);

            while (!socketChannel.finishConnect()) {
                log.info("connecting");
            }

            log.info("connected");
        }

        @Override
        public void run() {
            log.info("this Thread name:{}", Thread.currentThread().getName());


            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int select = selector.select();
                    if (select == 0) {
                        continue;
                    }

                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                    SelectionKey selectionKey = null;
                    while (iterator.hasNext()) {
                        selectionKey = iterator.next();
                        iterator.remove();

                        Buffers buffers = (Buffers) selectionKey.attachment();
                        ByteBuffer readBuffer = buffers.getReadBuffer();
                        ByteBuffer writeBuffer = buffers.getWriteBuffer();


                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        if (selectionKey.isReadable()) {
                            log.info("client 读取");

                            socketChannel.read(readBuffer);
                            readBuffer.flip();

                            CharBuffer charBuffer = charset.decode(readBuffer);

                            System.out.println(charBuffer.array());

                            readBuffer.clear();

                        }


//                        if (selectionKey.isWritable()) {
//                            log.info("client 写入");
//                            writeBuffer.put((Thread.currentThread().getName() + " " + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
//                            writeBuffer.flip();
//                            log.info("截止");
//                            socketChannel.write(writeBuffer);
//                            writeBuffer.clear();
//                            log.info("发送过去");
//                        }


                    }


                    Thread.sleep(1000 + random.nextInt(1000));


                }

            } catch (IOException e) {
                log.error(StringUtils.EMPTY, e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (selector != null){
                    try {
                        selector.close();
                    } catch (IOException e) {
                        log.error(StringUtils.EMPTY,e);
                    }
                }
            }
        }


        public void writeMessage(String info)throws Exception {
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            log.info("client 写入");
            writeBuffer.put((info).getBytes(StandardCharsets.UTF_8));
            writeBuffer.flip();
            log.info("截止");
            socketChannel.write(writeBuffer);
            writeBuffer.clear();
            log.info("发送过去");

        }
    }


    public static void main(String[] args) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);

        TcpEchoClient tcpEchoClient = new TcpEchoClient(inetSocketAddress);
        new Thread(tcpEchoClient, "线程 1").start();

        TimeUnit.SECONDS.sleep(2);
        tcpEchoClient.writeMessage("数据题");


//        new Thread(new TcpEchoClient(inetSocketAddress),"线程 2").start();
//
//        new Thread(new TcpEchoClient(inetSocketAddress),"线程 3").start();

    }
}
