package com.lesson.distributed.netty.quickstart.netty.simple.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author zhengshijun
 * @version created on 2019/8/30.
 */
@Slf4j
public class InboundHandler extends ChannelInboundHandlerAdapter {

    private String name;

    public InboundHandler(String name) {
        this.name = name;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        //log.info("InboundHandler1 channelRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //log.info("InboundHandler1 channelActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("InboundHandler"+name+" channelRead:{}",msg);

        ctx.fireChannelRead(msg);




    }
}
