package com.jayus.netty.nettyHttp.servlet;

import com.jayus.netty.nettyHttp.common.BaseServlet;
import com.jayus.netty.nettyHttp.common.HttpRequest;
import com.jayus.netty.nettyHttp.common.HttpResponse;

/**
 * @author : h zk
 * @date : 2022/10/26 18:18
 * @description :
 **/
public class SecondServlet extends BaseServlet {
    @Override
    public void doGet(HttpRequest request, HttpResponse res) {
        doPost(request,res);
    }

    @Override
    public void doPost(HttpRequest request, HttpResponse res) {
        String username = request.getParameter("username");
        res.write("用户名是" + username);
    }
}
