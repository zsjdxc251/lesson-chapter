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

            // 该操作是当前位置的 ChannelHandlerContext 开始  往上找
            ctx.writeAndFlush("你好");
            // 研究这两者的区别

            // 该操作是，获取该  pipeline tail 的ChannelHandlerContext  开始 往上找
            ctx.channel().writeAndFlush("你好");
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public String toString() {
        return "InboundHandler--"+name;
    }
}
