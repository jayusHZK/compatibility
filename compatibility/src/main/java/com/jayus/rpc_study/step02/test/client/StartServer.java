package com.jayus.rpc_study.step02.test.client;

import com.jayus.rpc_study.step02.server.ServerSocket;

/**
 * @ClassName StartServer
 * @Description:
 * @date: 2024/10/22 19:22
 */
public class StartServer {

    public static void main(String[] args) {
        System.out.println("client start begin");
        new Thread(new ServerSocket()).start();
        System.out.println("client start ok");
    }

}
