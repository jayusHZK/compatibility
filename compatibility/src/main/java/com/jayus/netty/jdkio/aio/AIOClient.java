package com.jayus.netty.jdkio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @author : h zk
 * @date : 2022/7/8 11:40
 * @description :
 **/
public class AIOClient {
    private int port = 8888;
    private String host = "127.0.0.1";
    private AsynchronousSocketChannel asynchronousSocketChannel;

    public AIOClient() {
        try {
            this.asynchronousSocketChannel = AsynchronousSocketChannel.open();
            this.asynchronousSocketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
            this.asynchronousSocketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
            this.asynchronousSocketChannel.connect(new InetSocketAddress(this.host, this.port));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 处理服务端返回消息的回调
            this.asynchronousSocketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    attachment.flip();
                    byte[] bytes = new byte[attachment.remaining()];
                    attachment.get(bytes);
                    String msg = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println(msg);
                    attachment.clear();
                    if ("bye".equals(msg)) {
                        try {
                            asynchronousSocketChannel.shutdownInput();
                            asynchronousSocketChannel.shutdownOutput();
                            asynchronousSocketChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        asynchronousSocketChannel.read(buffer, buffer, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            this.asynchronousSocketChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8))).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AIOClient aioClient = new AIOClient();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            aioClient.sendMessage(input);
            if ("bye".equals(input)) break;
        }
    }

}
