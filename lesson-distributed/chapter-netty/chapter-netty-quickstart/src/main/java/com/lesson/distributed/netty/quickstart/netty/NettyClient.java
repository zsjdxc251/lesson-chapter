package com.lesson.distributed.netty.quickstart.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
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

/**
 * @author zhengshijun
 * @version created on 2018/11/7.
 */
@Slf4j
public class NettyClient {

    public static class TcpEchoClient implements Runnable {



        @Override
        public void run() {

            EventLoopGroup group = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new StringDecoder());
                    channel.pipeline().addLast(new StringEncoder());
                    channel.pipeline().addLast(new ClientProcessHandler());
                }
            });

            try {
                ChannelFuture future =  bootstrap.connect(new InetSocketAddress("127.0.0.1",8080)).sync();
                Channel channel = future.channel();

                


            } catch (InterruptedException e) {
                log.error(StringUtils.EMPTY,e);
            }

        }
    }

    public static class ClientProcessHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("ClientProcessHandler channelActive");

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("ClientProcessHandler channelRead");

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.info("ClientProcessHandler exceptionCaught");
            ctx.close();
        }
    }

    public static void main(String[] args){


    }
}
