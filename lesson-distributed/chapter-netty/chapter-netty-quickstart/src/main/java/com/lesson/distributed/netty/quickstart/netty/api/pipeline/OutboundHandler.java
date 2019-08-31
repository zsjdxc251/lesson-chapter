package com.lesson.distributed.netty.quickstart.netty.api.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
        log.info(" write:{}", name);

        ctx.write(msg, promise);

    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       if (Objects.equals(name,"B")){
           ctx.executor().schedule(()->{
               ctx.write("say hello");
           },3, TimeUnit.SECONDS);
       }
    }

    @Override
    public String toString() {
        return "OutboundHandler---"+name;
    }
}
