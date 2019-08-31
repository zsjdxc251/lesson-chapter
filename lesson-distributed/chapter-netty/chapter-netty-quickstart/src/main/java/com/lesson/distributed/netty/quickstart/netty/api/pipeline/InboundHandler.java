package com.lesson.distributed.netty.quickstart.netty.api.pipeline;

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
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(" channelRead:{}",name);

        if (Objects.equals(name,"C")){
            ctx.writeAndFlush("你好");
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public String toString() {
        return "InboundHandler--"+name;
    }
}
