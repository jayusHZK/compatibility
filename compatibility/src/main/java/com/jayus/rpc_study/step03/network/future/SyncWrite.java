package com.jayus.rpc_study.step03.network.future;

import com.jayus.rpc_study.step03.network.msg.Request;
import com.jayus.rpc_study.step03.network.msg.Response;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName SyncWrite
 * @Description:
 * @date: 2024/10/22 21:27
 */
public class SyncWrite {

    public Response writeAndSync(final Channel channel, final Request request, final long timeout) throws Exception {
        if (channel == null) {
            if (channel == null) {
                throw new NullPointerException("channel");
            }
            if (request == null) {
                throw new NullPointerException("request");
            }
            if (timeout <= 0) {
                throw new IllegalArgumentException("timeout <= 0");
            }
        }

        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);
        SyncWriteFuture future = new SyncWriteFuture(request.getRequestId());
        SyncWriteMap.syncKey.put(request.getRequestId(), future);
        Response response = doWriteAndSync(channel, request, timeout, future);
        SyncWriteMap.syncKey.remove(request.getRequestId());
        return response;
    }

    private Response doWriteAndSync(final Channel channel, final Request request, final long timeout
            , final WriteFuture<Response> writeFuture) throws Exception {
        channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                writeFuture.setWriteResult(channelFuture.isSuccess());
                writeFuture.setCause(channelFuture.cause());
                if (!writeFuture.isWriteSuccess()) {
                    SyncWriteMap.syncKey.remove(writeFuture.requestId());
                }
            }
        });
        Response response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
        if (response == null) {
            if (writeFuture.isTimeout()) {
                throw new TimeoutException();
            } else {
                throw new Exception(writeFuture.cause());
            }
        }
        return response;
    }


}
