package com.jayus.netty.nettyHttp;

/**
 * @author : h zk
 * @date : 2022/10/28 18:24
 * @description :
 **/
public class HttpApplication {
    public static void main(String[] args) {
        new HttpServer(8080).start();
    }
}
