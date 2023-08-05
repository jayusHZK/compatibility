package com.jayus.file.io.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 本地 web 端口监听服务
 */
public class LocalWebServer {

    private static final boolean isRunning = true;

    static InputStream is = null;

    static OutputStream os = null;

    static BufferedReader br = null;

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8888);
        while (isRunning) {
            Socket socket = ss.accept();
            System.out.println("由请求进来了");
            try {
                process(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
            socket.close();
        }
    }

    private static void process(Socket s) throws Exception {
        is = s.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        String path = line.split(" ")[1];
        System.out.println("path = "+ path);
        while (line != null && !line.equals("")){
            System.out.println(line);
            line = br.readLine();
        }
        os = s.getOutputStream();
        System.out.println("读取数据结束");
        os.write("HTTP/1.1 200 OK\r\n".getBytes());
        os.write("Content-type:application/json;charset=utf-8;\r\n".getBytes());
        os.write("\r\n".getBytes());
        os.write("no data".getBytes());
        os.flush();
    }


}
