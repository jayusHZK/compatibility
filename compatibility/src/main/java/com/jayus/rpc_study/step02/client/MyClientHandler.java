package com.jayus.rpc_study.step02.client;

import com.jayus.rpc_study.step02.future.SyncWriteFuture;
import com.jayus.rpc_study.step02.future.SyncWriteMap;
import com.jayus.rpc_study.step02.msg.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName MyClientHandler
 * @Description:
 * @date: 2024/10/22 14:03
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response massage = (Response) msg;
        String requestId = massage.getRequestId();
        SyncWriteFuture future = (SyncWriteFuture) SyncWriteMap.syncKey.get(requestId);
        if (future != null) {
            future.setResponse(massage);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
