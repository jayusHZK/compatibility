package com.jayus.netty.nettyRpc.provider;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : h zk
 * @date : 2022/11/14 15:28
 * @description : 启动 RPC 服务
 **/
@Slf4j
public class Provider {

    private final int port;

    private final String host;

    private final ProviderRegister register = new ProviderRegister();

    public Provider(String host,int port){
        this.host = host;
        this.port = port;
    }

    public void start(){
        log.info("开始启动 RPC 服务，地址是 {} 端口号是：{}",host,port);
    }

}
