package com.jayus.netty.nettyHttp.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author : h zk
 * @date : 2022/10/26 16:21
 * @description :
 **/
public class HttpRequest {

    private final ChannelHandlerContext context;

    private final FullHttpRequest fullHttpRequest;

    private final Map<String, List<String>> parameters;

    public HttpRequest(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) {
        this.context = context;
        this.fullHttpRequest = fullHttpRequest;
        this.parameters = new QueryStringDecoder(fullHttpRequest.uri()).parameters();
        System.out.println("处理来自 " + context.channel().remoteAddress() + getUri() + " 的请求");
    }

    public String getUri() {
        return this.fullHttpRequest.uri().split("\\?")[0];
    }

    public String getMethod() {
        return fullHttpRequest.method().name();
    }

    public Map<String, List<String>> getParameters() {
        return this.parameters;
    }

    public String getParameter(String name) {
        if (this.parameters.get(name) != null) {
            return this.parameters.get(name).get(0);
        } else {
            return null;
        }
    }

    public FullHttpRequest getFullHttpRequest() {
        return fullHttpRequest;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

}
