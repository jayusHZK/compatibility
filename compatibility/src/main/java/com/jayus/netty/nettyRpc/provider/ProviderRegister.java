package com.jayus.netty.nettyRpc.provider;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : h zk
 * @date : 2022/11/14 15:29
 * @description : RPC 服务端的服务势力和对象
 **/
@Slf4j
public class ProviderRegister {

    private static final Map<String,Object> SERVICE_MAP = new ConcurrentHashMap<>();

    /**
     *  添加 RPC 端的服务
     * @param service
     * @param clazz
     * @param <T>
     */
    public <T> void addService(T service,Class<T> clazz){
        // getCanonicalName 是获取所传类从java语言规范定义的格式输出
        String serviceName = clazz.getCanonicalName();
        log.info("添加服务，名称是 {}",serviceName);
        SERVICE_MAP.put(serviceName,service);
    }

    public Object getService(String serviceName){
        Object service = SERVICE_MAP.get(serviceName);
        if (service == null){
            log.debug("没有找到该 RPC 服务");
            return null;
        }
        log.info("找到服务 {}",serviceName);
        return service;
    }

}
