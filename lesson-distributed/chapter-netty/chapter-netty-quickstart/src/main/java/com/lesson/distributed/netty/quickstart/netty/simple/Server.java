package com.lesson.distributed.netty.quickstart.netty.simple;

import com.lesson.distributed.netty.quickstart.netty.simple.handler.InboundHandler;
import com.lesson.distributed.netty.quickstart.netty.simple.handler.OutboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengshijun
 * @version created on 2019/8/28.
 */
@Slf4j
public class Server extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channelRead:{}",msg);

        ctx.writeAndFlush("处理:"+msg);

        // 事件往下传播
        ctx.fireChannelRead(msg);


    }



    public static void main(String[] args) {


        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

//                        ch.pipeline().addLast(new StringEncoder());
//                        ch.pipeline().addLast(new StringDecoder());

                        ch.pipeline().addLast(new InboundHandler("1"));
                        ch.pipeline().addLast(new InboundHandler("2"));
                        ch.pipeline().addLast(new InboundHandler("3"));

                        ch.pipeline().addLast(new OutboundHandler("1"));
                        ch.pipeline().addLast(new OutboundHandler("2"));
                        ch.pipeline().addLast(new OutboundHandler("3"));
                        ch.pipeline().addLast(new Server());


                    }
                });

        bootstrap.option(ChannelOption.SO_BACKLOG,128);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
        try {
            ChannelFuture future = bootstrap.bind(8081).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

