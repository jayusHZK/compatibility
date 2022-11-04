package com.jayus.netty.nettyHttp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.net.InetSocketAddress;

/**
 * @author : h zk
 * @date : 2022/10/26 18:20
 * @description :
 **/
public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start(){
        System.out.println("开启HTTP服务，端口是:" + port);
        // 一个 EventGroup 可以管理多个 channel
        EventLoopGroup executors = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(executors)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpServerCodec()); // http编解码
                            socketChannel.pipeline().addLast("HttpAggregator",new HttpObjectAggregator(512 * 1024)); // http消息聚合器
                            socketChannel.pipeline().addLast(new HttpRequestHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(new InetSocketAddress(this.port));
            future.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            try {
                executors.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
