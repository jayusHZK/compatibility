package com.jayus.netty.jdkio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : h zk
 * @date : 2022/7/11 10:13
 * @description : 单线程的reactor
 **/
public class BasicReactor implements Runnable {

    final Selector selector;

    final ServerSocketChannel serverSocket;

    public static void main(String[] args) throws IOException {
        BasicReactor basicReactor = new BasicReactor(8888);
        new Thread(basicReactor).start();
    }

    public BasicReactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (Thread.interrupted()) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keys = selectionKeys.iterator();
                while (keys.hasNext()) {
                    dispatch(keys.next());
                }
                selectionKeys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispatch(SelectionKey key) {
        Runnable r = (Runnable) key.attachment();
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel s = serverSocket.accept();
                if (s != null) {
                    new Handler(selector, s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class Handler implements Runnable {

        final SocketChannel socket;

        final SelectionKey sk;

        ByteBuffer input = ByteBuffer.allocate(1024);

        ByteBuffer output = ByteBuffer.allocate(1024);

        final int READING = 0, SENDING = 1;

        int stage = READING;

        public Handler(Selector sel, SocketChannel s) throws IOException {
            socket = s;
            socket.configureBlocking(false);
            sk = socket.register(sel, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }

        @Override
        public void run() {
            try {
                if (stage == READING) read();
                if (stage == SENDING) send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                stage = READING;
                sk.interestOps(SelectionKey.OP_READ);
            }
        }

        void send() throws IOException {
            output.flip();
            socket.write(output);
            if (outputIsComplete()) {
                output.clear();
                stage = READING;
                sk.interestOps(SelectionKey.OP_READ);
            }
        }

        void process() throws IOException {
            output.flip();
            socket.write(output);
            if (outputIsComplete()) {
                output.clear();
                stage = READING;
                sk.interestOps(SelectionKey.OP_READ);
            }
        }

        boolean inputIsComplete() {
            return input.hasRemaining();
        }

        boolean outputIsComplete() {
            return output.hasRemaining();
        }
    }
}
