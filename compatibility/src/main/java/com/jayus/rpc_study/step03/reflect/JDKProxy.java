package com.jayus.rpc_study.step03.reflect;

import com.jayus.rpc_study.step03.network.msg.Request;
import com.jayus.rpc_study.step03.util.ClassLoaderUtils;

import java.lang.reflect.Proxy;

/**
 * @ClassName JDKProxy
 * @Description:
 * @date: 2024/10/22 22:02
 */
public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, Request request) {
        JDKInvocationHandler handler = new JDKInvocationHandler(request);
        ClassLoader classLoader = ClassLoaderUtils.getCurrentClassLoader();
        T result = (T) Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, handler);
        return result;
    }

}
