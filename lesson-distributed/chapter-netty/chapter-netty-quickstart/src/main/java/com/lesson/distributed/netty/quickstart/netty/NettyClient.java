package com.lesson.distributed.netty.quickstart.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhengshijun
 * @version created on 2018/11/7.
 */
@Slf4j
public class NettyClient {

    public static class TcpEchoClient implements Runnable {


        private Bootstrap bootstrap;
        private EventLoopGroup group;

        private ThreadLocal<Channel> channelLocal = new ThreadLocal<>();
        private CountDownLatch countDownLatch = new CountDownLatch(1);
        private volatile boolean stopWrite = false;

        public TcpEchoClient() {
            group = new NioEventLoopGroup();

            bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new StringDecoder());
                    channel.pipeline().addLast(new StringEncoder());

                    ClientProcessHandler clientProcessHandler = new ClientProcessHandler();

                    // 如果 ClientProcessHandler 不 添加 @ChannelHandler.Sharable 添加两次会报错
                    channel.pipeline().addLast(clientProcessHandler);

                    channel.pipeline().addLast(clientProcessHandler);
                }
            });
        }

        @Override
        public void run() {

            log.info("bootstrap:{}",bootstrap.hashCode());

            try {
                ChannelFuture future =  bootstrap.connect(new InetSocketAddress("127.0.0.1",8081)).sync();
                try {
                    channelLocal.set(future.channel());
                } finally {
                    countDownLatch.countDown();
                }
                log.info("closeFuture before");



                channelLocal.get().closeFuture().sync();
                log.info("closeFuture after");
            } catch (InterruptedException e) {
                log.error(StringUtils.EMPTY,e);
            } finally {
                group.shutdownGracefully();
                stopWrite = true;

            }

        }

        private void writeAndFlush(String message) throws InterruptedException {
            if (stopWrite){
                Thread.currentThread().interrupt();
                return;
            }
            countDownLatch.await();
            if (channelLocal.get() == null){
                throw new NullPointerException();
            }
            channelLocal.get().writeAndFlush(message);

        }
    }

    @ChannelHandler.Sharable
    public static class ClientProcessHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("ClientProcessHandler channelActive");



        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("ClientProcessHandler channelRead");

            log.info("ClientProcessHandler msg:{}",msg);



        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            log.info("ClientProcessHandler channelUnregistered");
            // 关闭连接 触发 closeFuture
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.info("ClientProcessHandler exceptionCaught");
            ctx.close();
        }
    }

    public static void main(String[] args){
        TcpEchoClient tcpEchoClient = new TcpEchoClient();
        Thread thread = new Thread(tcpEchoClient);
        thread.start();

        thread = new Thread(tcpEchoClient);
        thread.start();

        thread = new Thread(tcpEchoClient);
        thread.start();

        thread = new Thread(tcpEchoClient);
        thread.start();

//        while (!Thread.currentThread().isInterrupted()) {
//            Scanner scanner = new Scanner(System.in);
//            String msg = scanner.nextLine();
//            log.info("console println:{}",msg);
//            try {
//                tcpEchoClient.writeAndFlush(msg);
//            } catch (InterruptedException e) {
//                log.error(StringUtils.EMPTY,e);
//            }
//
//        }


    }
}
