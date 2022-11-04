package com.jayus.netty.jdkio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author : h zk
 * @date : 2022/7/8 15:15
 * @description :
 **/
public class WakeUpSelectorTest implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private final int port = 8888;

    private boolean isStart = false;

    public WakeUpSelectorTest() {
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(new InetSocketAddress(port));
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            this.isStart = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        WakeUpSelectorTest wakeUpSelectorTest = new WakeUpSelectorTest();
        Thread serverThread = new Thread(wakeUpSelectorTest);
        Thread wakeupThread = new Thread(() -> {
            try {
                Thread.sleep(10000);
                wakeUpSelectorTest.getSelector().wakeup();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        });
        serverThread.start();
        wakeupThread.start();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (this.isStart) {
            try {
                this.selector.select();
                System.out.println("Selector Wakeup");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Selector getSelector() {
        return selector;
    }
}
