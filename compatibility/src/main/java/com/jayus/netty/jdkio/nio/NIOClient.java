package com.jayus.netty.jdkio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author : h zk
 * @date : 2022/7/8 15:15
 * @description :
 **/
public class NIOClient implements Runnable {

    // 多路复用器
    private Selector selector;

    // 客户端的 socketChannel
    private SocketChannel socketChannel;

    private String host = "127.0.0.1";

    private int post = 8888;

    private volatile boolean isStart = false;

    public NIOClient() {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            this.socketChannel.configureBlocking(false);
            // 连接服务器
            socketChannel.connect(new InetSocketAddress(host, post));
            // 注册到选择器，并注册 OP_CONNETC 去连接服务器
            this.socketChannel.register(this.selector, SelectionKey.OP_CONNECT);
            this.isStart = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NIOClient nioClient = new NIOClient();
        new Thread(nioClient).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            nioClient.sendMessage(input);
            if ("bye".equals(input)) {
                break;
            }
        }
    }

    @Override
    public void run() {
        while (this.isStart) {
            try {
                this.selector.select();
                Iterator<SelectionKey> selectionKeys = this.selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {
                    SelectionKey selectionKey = selectionKeys.next();
                    selectionKeys.remove();
                    if (selectionKey.isConnectable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        if (!channel.finishConnect()) {
                            System.exit(1);
                        }
                    }
                    if (selectionKey.isReadable()) {
                        read(selectionKey);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    发送消息
     */
    public void sendMessage(String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        try {
            this.socketChannel.register(this.selector, SelectionKey.OP_READ);
            this.socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey selectionKey) {
        // 处理服务端发送来的消息
        // 获得服务端连接
        // 创建 ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int readBytes = this.socketChannel.read(buffer);
            if (readBytes > 0) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                String message = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(message + ", from" + this.socketChannel.getRemoteAddress());
                if ("bye".equals(message)) {
                    selectionKey.cancel();
                    this.socketChannel.close();
                    this.isStart = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
