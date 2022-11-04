package com.jayus.netty.nettyHttp.common;

/**
 * @author : h zk
 * @date : 2022/10/26 17:35
 * @description :
 **/
public abstract class BaseServlet {

    private final static String GET = "GET";

    public void service(HttpRequest request, HttpResponse response) {
        if (GET.equalsIgnoreCase(request.getMethod())) {
            doGet(request,response);
        } else {
            doPost(request,response);
        }
    }

    public abstract void doGet(HttpRequest request,HttpResponse res);

    public abstract void doPost(HttpRequest request,HttpResponse res);

}

