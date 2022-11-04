package com.jayus.netty.nettyHttp;

import com.jayus.netty.nettyHttp.common.BaseServlet;
import com.jayus.netty.nettyHttp.common.HttpRequest;
import com.jayus.netty.nettyHttp.common.HttpResponse;
import com.jayus.netty.nettyHttp.common.HttpServletMapping;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Map;

/**
 * @author : h zk
 * @date : 2022/10/26 18:21
 * @description :
 **/
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        HttpRequest request = new HttpRequest(channelHandlerContext, fullHttpRequest);
        String uri = request.getUri();
        HttpResponse response = new HttpResponse(channelHandlerContext, request);
        Map<String, BaseServlet> servletMapping = HttpServletMapping.getServletMapping();
        if (servletMapping != null && servletMapping.containsKey(uri)) {
            servletMapping.get(uri).service(request, response);
        }else {
            response.write("404 -- Not Found");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }
}
