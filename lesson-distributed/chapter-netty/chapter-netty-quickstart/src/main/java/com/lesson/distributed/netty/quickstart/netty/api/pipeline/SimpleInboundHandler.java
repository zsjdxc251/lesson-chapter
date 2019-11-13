package com.lesson.distributed.netty.quickstart.netty.api.pipeline;

import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author zhengshijun
 * @version created on 2019/8/30.
 */
@Slf4j
public class SimpleInboundHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        DefaultChannelPromise promise = new DefaultChannelPromise(ctx.channel());
        ChannelFuture channelFuture = ctx.writeAndFlush("111111111",promise);


        // true
        System.out.println(channelFuture == promise);
    }


}
