package com.jayus.netty.jdkio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : h zk
 * @date : 2022/7/8 10:10
 * @description :
 **/
public class AIOServer {

    // 服务端的channel
    private AsynchronousServerSocketChannel asynServerSocketChannel;

    // channel 的线程池
    private AsynchronousChannelGroup asynchronousChannelGroup;

    //端口
    private int port = 8888;

    // 线程池
    private ExecutorService executorService;

    public AIOServer() {
        try {
            this.executorService = Executors.newCachedThreadPool();
            this.asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(this.executorService, 1);
            this.asynServerSocketChannel = AsynchronousServerSocketChannel.open(this.asynchronousChannelGroup);
            this.asynServerSocketChannel.bind(new InetSocketAddress(this.port));
            this.asynServerSocketChannel.accept(this,new AIOServerHandler());
            System.out.println("aio server start --");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel getAsynServerSocketChannel(){return this.asynServerSocketChannel;}

    public static void main(String[] args) {
        new AIOServer();
    }
}
