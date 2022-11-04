package com.jayus.netty.jdkio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : h zk
 * @date : 2022/7/8 15:15
 * @description :
 **/
public class NIOServer implements Runnable {

    static AtomicInteger a = new AtomicInteger(1);

    // Java nio 编程核心 多路复用器
    private Selector selector;

    // 服务器的 channel
    private ServerSocketChannel serverSocketChannel;

    private final int port = 8888;

    private boolean isStart = false;

    public NIOServer() {
        try {
            // 创建多路复用器
            this.selector = Selector.open();
            // 开启服务端 channel 设置为非阻塞的 监听端口 8888
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(new InetSocketAddress(port));
            // 将服务端的 channel 注册到selector 监听ON_ACCEPT 事件
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("NIO Server Start" + this.serverSocketChannel.getLocalAddress());
            this.isStart = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // 不断轮询注册到 selector 的事件是否就绪
        while (this.isStart) {
            try {
                // 这里阻塞到至少有一个事件就绪
                this.selector.select();
                // selector.select(long timeout); 表示最多阻塞多长时间，没有就绪的事件就timeout
                // 多路复用已经就绪的结果集
                Iterator<SelectionKey> selectionKeys = this.selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {
                    // 取出一个就绪的key
                    SelectionKey selectionKey = selectionKeys.next();
                    selectionKeys.remove();
                    // 新建立连接
                    if (selectionKey.isAcceptable()) {
                        accept(selectionKey);
                    }
                    // 读取客户端输入
                    if (selectionKey.isReadable()) {
                        read(selectionKey);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey selectionKey) {
        // 处理客户端发送来的消息
        // 获得客户端连接的 SocketChannel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 创建 byteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int readBytes = socketChannel.read(buffer);
            if (readBytes > 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                String message = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(message + ", from" + socketChannel.getRemoteAddress());
                buffer.rewind();
                socketChannel.write(buffer);
                if ("bye".equals(message)) {
                    System.out.println("Client close " + socketChannel.getRemoteAddress());
                    selectionKey.cancel();
                    socketChannel.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accept(SelectionKey selectionKey) {
        // 处理新建立的连接
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(this.selector, SelectionKey.OP_READ);
            System.out.println("New Client From" + socketChannel.getRemoteAddress() + ",a----" + a.getAndIncrement());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new NIOServer()).start();
    }

}
