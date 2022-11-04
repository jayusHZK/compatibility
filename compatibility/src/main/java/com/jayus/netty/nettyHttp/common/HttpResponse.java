package com.jayus.netty.nettyHttp.common;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.util.StringUtils;

/**
 * @author : h zk
 * @date : 2022/10/26 16:32
 * @description :
 **/
public class HttpResponse {

    private ChannelHandlerContext context;

    private HttpRequest request;

    public HttpResponse(ChannelHandlerContext context, HttpRequest request) {
        this.context = context;
        this.request = request;
    }

    public void write(String out) {
        if (!StringUtils.isEmpty(out)){
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
            , Unpooled.copiedBuffer(out, CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/html; charset=UTF-8");
            context.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }
}
