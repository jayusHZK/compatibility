package com.jayus.rpc_study.step02.server;

import com.jayus.rpc_study.step02.msg.Request;
import com.jayus.rpc_study.step02.msg.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @ClassName MyServerHandler
 * @Description:
 * @date: 2024/10/22 19:14
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        Request msg = (Request) obj;
        // 反馈
        Response request = new Response();
        request.setRequestId(msg.getRequestId());
        request.setParam(msg.getResult() + "请求成功，反馈结果请接收处理");
        ctx.writeAndFlush(request);
        // 释放
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
