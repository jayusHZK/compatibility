package com.jayus.smallMyBatis.step19.plugin;

import java.util.Properties;

/**
 * @ClassName Interceptor
 * @Description: 拦截器接口
 * @date: 2024/10/17 23:37
 */
public interface Interceptor {

    // 拦截，使用方实现
    Object intercept(Invocation invocation) throws Throwable;

    // 代理
    default Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    // 设置属性
    default void setProperties(Properties properties){

    }
}
