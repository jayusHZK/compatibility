package com.jayus.netty.nettyHttp.servlet;

import com.jayus.netty.nettyHttp.common.BaseServlet;
import com.jayus.netty.nettyHttp.common.HttpRequest;
import com.jayus.netty.nettyHttp.common.HttpResponse;

/**
 * @author : h zk
 * @date : 2022/10/26 18:17
 * @description :
 **/
public class HelloServlet extends BaseServlet {
    @Override
    public void doGet(HttpRequest request, HttpResponse res) {
        doPost(request,res);
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse res) {
        res.write("Hello,");
    }
}
