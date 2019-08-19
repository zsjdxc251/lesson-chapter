package com.lesson.distributed.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 *
 *      编码
 *     {@link HttpObjectEncoder}
 *            {@link HttpResponseEncoder<HttpResponse>}
 *                   {@link MessageToMessageEncoder<Object>}
 *                          {@link ChannelOutboundHandlerAdapter}
 *      解码
 *     {@link HttpRequestDecoder}
 *            {@link HttpObjectDecoder}
 *
 *                  {@link ByteToMessageDecoder}
 *                         {@link ChannelInboundHandlerAdapter}
 *
 *     {@link ChannelPipeline}
 * @author zhengshijun
 * @version created on 2018/11/10.
 */
@Slf4j
public class FileBootstrapServer {


    public static class TcpEchoServer extends Thread{

        private int port;

        private String prefix;

        public TcpEchoServer(int port, String prefix) {
            this.port = port;
            this.prefix = prefix;
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

                    pipeline.addLast(new HttpRequestDecoder());



                    pipeline.addLast(new HttpObjectAggregator(65536));

                    pipeline.addLast(new HttpResponseEncoder());


                    pipeline.addLast(new ChunkedWriteHandler());

                    pipeline.addLast(new FileServerHandler(prefix));

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



    public static class FileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        private String filePrefix;

        public FileServerHandler(String filePrefix) {
            this.filePrefix = filePrefix;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {


            if (!request.getDecoderResult().isSuccess()){
                return;
            }


            String uri = request.getUri();
            uri = URLDecoder.decode(uri, StandardCharsets.UTF_8.displayName());

            log.info("uri:{}",uri);

            File file = new File(filePrefix,uri);

            if (file.isHidden() || !file.exists()){
                return;
            }


            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");

            long fileLength = randomAccessFile.length();

            HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK);

            response.headers().add("content-length",fileLength);
            response.headers().add("content-type","text/html;charset=UTF-8");

            ctx.write(response);

            ChannelFuture future =
                    ctx.write(new ChunkedFile(randomAccessFile,0,fileLength,8192),ctx.newProgressivePromise());

            future.addListener(new ChannelProgressiveFutureListener() {
                @Override
                public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {

                    log.info("progress:{},total:{}",progress,total);




                }

                @Override
                public void operationComplete(ChannelProgressiveFuture future) throws Exception {

                }
            });



            ctx.flush();


        }
    }



    public static void main(String[] args){

        new TcpEchoServer(8080,"F:\\tmp").start();
    }
}
