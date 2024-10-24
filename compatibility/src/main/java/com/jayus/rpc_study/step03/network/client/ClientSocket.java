package com.jayus.rpc_study.step03.network.client;

import com.jayus.rpc_study.step03.network.codec.RpcDecoder;
import com.jayus.rpc_study.step03.network.codec.RpcEncoder;
import com.jayus.rpc_study.step03.network.msg.Request;
import com.jayus.rpc_study.step03.network.msg.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName ClientSocket
 * @Description:
 * @date: 2024/10/22 21:04
 */
public class ClientSocket implements Runnable {

    private ChannelFuture future;

    private String inetHost;

    private int inetPort;

    public ClientSocket(String inetHost, int inetPort) {
        this.inetHost = inetHost;
        this.inetPort = inetPort;
    }

    @Override
    public void run() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new RpcDecoder(Response.class),
                                    new RpcEncoder(Request.class),
                                    new MyClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(inetHost, inetPort);
            this.future = f;
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public ChannelFuture getFuture() {
        return future;
    }

    public void setFuture(ChannelFuture future) {
        this.future = future;
    }
}
