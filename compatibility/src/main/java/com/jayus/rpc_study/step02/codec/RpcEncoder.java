package com.jayus.rpc_study.step02.codec;

import com.jayus.rpc_study.step02.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName RpcEncoder
 * @Description:
 * @date: 2024/10/22 14:00
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        if (genericClass.isInstance(object)) {
            byte[] data = SerializationUtil.serialize(object);
            byteBuf.writeInt(data.length);
            byteBuf.writeBytes(data);
        }
    }
}
