package com.jayus.netty.jdkio.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author : h zk
 * @date : 2022/7/11 10:00
 * @description :
 **/
public class DoubleFlipTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello".getBytes(StandardCharsets.UTF_8));
        System.out.println("no flip : limit ->" + buffer.limit() + ", position - > " + buffer.position());
        buffer.flip();
        System.out.println("flip1 : limit ->" + buffer.limit() + ", position - > " + buffer.position());
        buffer.flip();
        System.out.println("flip2 : limit ->" + buffer.limit() + ", position - > " + buffer.position());
    }
}
