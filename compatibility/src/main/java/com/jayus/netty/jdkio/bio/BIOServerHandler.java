package com.jayus.netty.jdkio.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author : h zk
 * @date : 2022/7/8 14:35
 * @description :
 **/
public class BIOServerHandler implements Runnable{

    // 与客户端连接的wocket
    private final Socket socket;

    public BIOServerHandler(Socket socket) {this.socket = socket;}

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            while (true) {
                String line = in.readLine();
                if (line != null) {
                    out.println(line +",from"+ socket.getRemoteSocketAddress());
                    if ("bye".equals(line)) break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in !=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out !=null){
                out.close();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
