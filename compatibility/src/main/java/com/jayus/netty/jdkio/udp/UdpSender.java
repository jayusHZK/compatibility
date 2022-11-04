package com.jayus.netty.jdkio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;

/**
 * @author : h zk
 * @date : 2022/7/11 16:55
 * @description :
 **/
public class UdpSender {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1",8888));
        ByteBuffer buffer = ByteBuffer.wrap("Hello UDP".getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
    }
}
