package com.lesson.distributed.netty.quickstart.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author zhengshijun
 * @version created on 2018/11/7.
 */
@Slf4j
public class NettyServer {


    public static class TcpEchoServer implements Runnable{

        private int port;

        public TcpEchoServer(int port) {
            this.port = port;
        }

        @Override
        public void run() {

            EventLoopGroup masterGroup = new NioEventLoopGroup(1);

            EventLoopGroup workerGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap =  new ServerBootstrap().group(masterGroup,workerGroup).channel(ServerSocketChannel.class).localAddress(new InetSocketAddress(port))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) {

                    channel.pipeline().addLast(new StringDecoder());
                    channel.pipeline().addLast(new StringEncoder());
                    channel.pipeline().addLast(new ServerProcessHandler());
                }
            });
            serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);



        }
    }

    private static class ServerProcessHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

            log.info("ServerProcessHandler channelRegistered");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("ServerProcessHandler channelRead");
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.info("ServerProcessHandler channelRead");
            cause.printStackTrace();
            ctx.close();
        }

    }


    public static void main(String[] args){





    }
}
