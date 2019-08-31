package com.lesson.distributed.netty.quickstart.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 *
 * {@link AbstractChannelHandlerContext#connect(java.net.SocketAddress, java.net.SocketAddress)}
 * @author zhengshijun
 * @version created on 2019/8/28.
 */
@Slf4j
public class Client extends ChannelInboundHandlerAdapter {


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {



    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelRegistered");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        log.info("channelRead");
    }

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ch.pipeline().addLast(new StringDecoder());

                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new Client());

                    }
                });
        try {


            ChannelFuture future =  bootstrap.connect(new InetSocketAddress("127.0.0.1",8081)).sync();
            //DefaultChannelPromise promise = new DefaultChannelPromise(future.channel());

            for (int i=0;i<1;i++) {
                future.channel().writeAndFlush("你好"+i);
                TimeUnit.SECONDS.sleep(1);
            }

            //promise.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}

