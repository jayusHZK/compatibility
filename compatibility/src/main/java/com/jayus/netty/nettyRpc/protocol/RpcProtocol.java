package com.jayus.netty.nettyRpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : h zk
 * @date : 2022/11/14 14:19
 * @description : 发起 RPC 请求格式 需要如下字段
 **/
@Data
public class RpcProtocol implements Serializable {

    private static final long serialVersionUID = 123;

    private String interfaceName;

    private String methodName;

    private Object[] paramValues;

    private Class<?>[] paramTypes;
}
