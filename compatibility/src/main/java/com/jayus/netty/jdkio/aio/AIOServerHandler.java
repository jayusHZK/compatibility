package com.jayus.netty.jdkio.aio;

import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;


/**
 * @author : h zk
 * @date : 2022/7/8 10:21
 * @description :
 **/
public class AIOServerHandler implements CompletionHandler<AsynchronousSocketChannel, AIOServer> {

    /*
        新连接进来调用
     */
    @Override
    public void completed(AsynchronousSocketChannel asyncChannel, AIOServer aioServer) {
        aioServer.getAsynServerSocketChannel().accept(aioServer, this);
        try {
            asyncChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            asyncChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
            asyncChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
            asyncChannel.setOption(StandardSocketOptions.SO_RCVBUF, 1024);
            asyncChannel.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        read(asyncChannel);
    }

    private void read(AsynchronousSocketChannel asyncChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // asyncChannel.read 只是注册一次回调，这次回调完成后还需要继续向操作系统注册新的 read回调
        asyncChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            // 处理请求
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                byte[] bytes = new byte[attachment.remaining()];
                attachment.get(bytes);
                String msg = new String(bytes, StandardCharsets.UTF_8);
                try {
                    System.out.println(msg + ",from" + asyncChannel.getRemoteAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachment.rewind();
                try {
                    asyncChannel.write(attachment).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                attachment.clear();
                if ("bye".equals(msg)) {
                    try {
                        asyncChannel.shutdownInput();
                        asyncChannel.shutdownOutput();
                        asyncChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    asyncChannel.read(attachment, attachment, this);
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    @Override
    public void failed(Throwable exc, AIOServer attachment) {
        exc.printStackTrace();
    }
}
