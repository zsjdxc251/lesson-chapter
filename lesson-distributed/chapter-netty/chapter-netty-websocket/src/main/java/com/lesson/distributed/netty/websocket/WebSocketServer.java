package com.lesson.distributed.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 *           Websocket Handler
 *           {@link WebSocketFrame}
 *                 {@link CloseWebSocketFrame} 关闭链路
 *                 {@link BinaryWebSocketFrame} 二进制消息
 *                 {@link ContinuationWebSocketFrame}
 *                 {@link PingWebSocketFrame}  ping消息
 *                 {@link PongWebSocketFrame}
 *                 {@link TextWebSocketFrame}  文本消息
 *
 *
 * @author zhengshijun
 * @version created on 2018/11/10.
 */
@Slf4j
public class WebSocketServer {

    public static class TcpEchoServer extends Thread {

        private int port;

        public TcpEchoServer(int port) {
            this.port = port;
        }

        @Override
        public void run() {

            EventLoopGroup masterGroup = new NioEventLoopGroup();

            EventLoopGroup workerGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(masterGroup,workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new HttpServerCodec());

                    pipeline.addLast(new HttpObjectAggregator(65536));

                    pipeline.addLast(new ChunkedWriteHandler());

                    pipeline.addLast(new RequestHandler());



                }
            });


            try {
                ChannelFuture future = serverBootstrap.bind(port).sync();
                log.info("server started port:{}",port);
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {

                log.error(StringUtils.EMPTY,e);
            } finally {
                masterGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }



        }
    }


    public static class RequestHandler extends ChannelInboundHandlerAdapter {


        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


            log.info("msg:{}",msg.getClass());

            if (msg instanceof FullHttpRequest){
                FullHttpRequest request = FullHttpRequest.class.cast(msg);

                log.info("http:{},{},uri:{},method:{},protocolVersion:{}",request.getDecoderResult().isSuccess(),request.headers().get("Upgrade"),request.getUri(),request.getMethod(),request.getProtocolVersion());


                HttpHeaders headers =  request.headers();
                headers.forEach(header->{
                    log.info("{}:{}",header.getKey(),header.getValue());
                });



                WebSocketServerHandshakerFactory factory
                        = new WebSocketServerHandshakerFactory("ws://localhost:8080",null,false);
                WebSocketServerHandshaker handsaker = factory.newHandshaker(request);


                if (handsaker == null){

                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                } else {

                    handsaker.handshake(ctx.channel(),request);
                }



            } else if (msg instanceof WebSocketFrame){

                log.info("websocket");
            }




        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            log.info("channelRegistered");
        }

        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            log.info("channelUnregistered");
        }

        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            log.info("handlerRemoved");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }
    }


    public static void main(String[] args){

        new TcpEchoServer(8080).start();


    }
}
