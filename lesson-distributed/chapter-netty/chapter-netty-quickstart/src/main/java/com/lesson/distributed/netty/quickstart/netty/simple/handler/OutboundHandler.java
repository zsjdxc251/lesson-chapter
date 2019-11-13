package com.lesson.distributed.netty.quickstart.netty.simple.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengshijun
 * @version created on 2019/8/30.
 */
@Slf4j
public class OutboundHandler extends ChannelOutboundHandlerAdapter {


    private String name;


    public OutboundHandler(String name) {
        this.name = name;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("OutboundHandler"+name+" write:{}",msg);

      ctx.write(msg,promise);

    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        log.info("OutboundHandler"+name+" flush");

        ctx.flush();
    }
}
