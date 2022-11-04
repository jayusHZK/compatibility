package com.jayus.netty.jdkio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : h zk
 * @date : 2022/7/11 11:43
 * @description :
 **/
public class MultipleReactor implements Runnable {

    public static ExecutorService pool = Executors.newFixedThreadPool(5);

    final Selector selector;

    final ServerSocketChannel serverSocket;

    public MultipleReactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.configureBlocking(false);
        serverSocket.socket().bind(new InetSocketAddress(port));
        SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        System.out.println("MultipleReactor run");
        while (!Thread.interrupted()) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keys = selectionKeys.iterator();
                while (keys.hasNext()) {
                    dispatch(keys.next());
                    //keys.remove();
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
        Selector[] subSelectors;

        int next = 0;

        public Acceptor() {
            subSelectors = new Selector[3];
            for (int i = 0; i < subSelectors.length; i++) {
                try {
                    subSelectors[i] = Selector.open();
                    new Thread(new SubReactor(subSelectors[i])).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            System.out.println("Acceptor run");
            try {
                SocketChannel s = serverSocket.accept();
                if (s != null) {
                    new Handler(subSelectors[next], s).run();
                }
                if (++next == subSelectors.length) next = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SubReactor implements Runnable {

        Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            System.out.println("SubReactor run");
            while (Thread.interrupted()) {
                try {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keys = selectionKeys.iterator();
                    while (keys.hasNext()) {
                        subDispatch(keys.next());
                    }
                    selectionKeys.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void subDispatch(SelectionKey key) {
            Runnable r = (Runnable) key.attachment();
            if (r != null) {
                r.run();
            }
        }
    }

    class Handler implements Runnable {

        final SocketChannel socket;

        final SelectionKey sk;

        final Selector selector;

        ByteBuffer input = ByteBuffer.allocate(1024);

        ByteBuffer output = ByteBuffer.allocate(1024);

        final int READING = 0, SENDING = 1, PROCESSING = 3;

        int stage = READING;

        public Handler(Selector sel, SocketChannel s) throws IOException {
            socket = s;
            socket.configureBlocking(false);
            sk = socket.register(sel, SelectionKey.OP_READ);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
            selector = sel;
        }

        @Override
        public void run() {
            System.out.println("Handler run");
            try {
                if (stage == READING) read();
                if (stage == SENDING) send();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        synchronized void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                stage = PROCESSING;
                MultipleReactor.pool.execute(new Processer());
            }
        }

        synchronized void send() throws IOException {
            output.flip();
            socket.write(output);
            if (outputIsComplete()) {
                output.clear();
                stage = READING;
                sk.interestOps(SelectionKey.OP_READ);
            }
        }

        void process() {
            input.flip();
            byte[] bytes = new byte[input.remaining()];
            input.get(bytes);
            String message = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(message);
            String request = "Server Re:" + message;
            input.compact();
            output.put(request.getBytes(StandardCharsets.UTF_8));
        }

        synchronized void processAndHandOff() {
            process();
            stage = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
            selector.wakeup();
        }

        public boolean inputIsComplete() {
            return input.hasRemaining();
        }

        public boolean outputIsComplete() {
            return output.hasRemaining();
        }

        class Processer implements Runnable {
            @Override
            public void run() {
                System.out.println("Processer run");
                processAndHandOff();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // MultipleReactor -> Acceptor -> SubReactor & Handler
        MultipleReactor multipleReactor = new MultipleReactor(8888);
        new Thread(multipleReactor).start();
    }
}
