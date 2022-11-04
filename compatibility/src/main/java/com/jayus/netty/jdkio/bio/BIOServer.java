package com.jayus.netty.jdkio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : h zk
 * @date : 2022/7/8 14:34
 * @description :
 **/
public class BIOServer {
    public static void main(String[] args) {
        int port = 8888;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                //
                new Thread(new BIOServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
