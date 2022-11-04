package com.jayus.netty.jdkio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2022/7/11 16:51
 * @description :
 **/
public class UdpReceiver {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(8888));
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        while (!Thread.interrupted()) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isReadable()) {
                    channel = (DatagramChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.receive(byteBuffer);
                    System.out.println(new String(byteBuffer.array(),0,byteBuffer.position()));
                }
                it.remove();
            }
            channel.close();
        }
    }
}
