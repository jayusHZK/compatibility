package com.jayus.netty.jdkio.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : h zk
 * @date : 2022/7/8 14:34
 * @description :
 **/
public class BIOClient {
    public static void main(String[] args) {
        int port = 8888;
        String host = "127.0.0.1";
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            // 和服务端建立 socket 连接
            socket = new Socket(host,port);
            // socket 的输入和输出流：输入是服务端发来的 输出是发到服务端的
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            while (scanner.hasNext()) {
                String scannerInput = scanner.nextLine();
                writer.println(scannerInput);
                String response = reader.readLine();
                System.out.println(response);
                if ("bye".equals(scannerInput)) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null){
                writer.close();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
