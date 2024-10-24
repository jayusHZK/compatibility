package com.jayus.rpc_study.step03.network.msg;

import io.netty.channel.Channel;

/**
 * @ClassName Response
 * @Description:
 * @date: 2024/10/22 21:19
 */
public class Response {

    private transient Channel channel;

    private String requestId;

    private Object result;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
