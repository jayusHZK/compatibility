package com.jayus.rpc_study.step02.client;

import com.jayus.rpc_study.step02.codec.RpcDecoder;
import com.jayus.rpc_study.step02.codec.RpcEncoder;
import com.jayus.rpc_study.step02.msg.Request;
import com.jayus.rpc_study.step02.msg.Response;
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
 * @date: 2024/10/22 13:53
 */
public class ClientSocket implements Runnable {

    private ChannelFuture future;

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
            ChannelFuture f = b.connect("127.0.0.1", 7397).sync();
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
