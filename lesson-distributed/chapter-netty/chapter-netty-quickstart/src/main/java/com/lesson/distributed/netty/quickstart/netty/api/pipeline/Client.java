package com.lesson.distributed.netty.quickstart.netty.api.pipeline;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2019/8/31.
 */
public class Client {

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
                    }
                });
        try {


            ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8081)).sync();

            for (int i = 0; i < 1; i++) {
                future.channel().writeAndFlush("你好" + i);
                TimeUnit.SECONDS.sleep(1);
            }

            TimeUnit.DAYS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}

