package com.lesson.distributed.netty.quickstart.netty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

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

            ThreadFactory masterBuilder = new ThreadFactoryBuilder().setNameFormat("masterGroup-%d").build();
            EventLoopGroup masterGroup = new NioEventLoopGroup(1,masterBuilder);

            ThreadFactory workerBuilder = new ThreadFactoryBuilder().setNameFormat("workerGroup-%d").build();

            EventLoopGroup workerGroup = new NioEventLoopGroup(1,workerBuilder);

            ServerBootstrap serverBootstrap =  new ServerBootstrap();
            serverBootstrap.group(masterGroup,workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) {
                    log.info("channel:{}",channel.getClass().getName());

                    channel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                    channel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                    channel.pipeline().addLast(new ServerProcessHandler());
                }
            });
            serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);

           try {
               ChannelFuture future = serverBootstrap.bind(port).sync();

               System.out.println("Server start listen at " + port );
               future.channel().closeFuture().sync();
           } catch (InterruptedException e) {
               log.error(StringUtils.EMPTY,e);
           }finally {
               masterGroup.shutdownGracefully();
               workerGroup.shutdownGracefully();
           }

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

            log.info("ServerProcessHandler msg:{}",msg);

            ctx.channel().writeAndFlush("处理之后的："+msg);

            ctx.close();


            Iterator<Map.Entry<Thread,StackTraceElement[]>> iterator = Thread.getAllStackTraces().entrySet().iterator();

            while (iterator.hasNext()) {

                Map.Entry<Thread,StackTraceElement[]> entry = iterator.next();

                System.out.println(entry.getKey().getName());
            }
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.info("ServerProcessHandler channelRead");
            cause.printStackTrace();
            ctx.close();
        }


    }


    public static void main(String[] args){



        new Thread(new TcpEchoServer(8080)).start();


    }
}
