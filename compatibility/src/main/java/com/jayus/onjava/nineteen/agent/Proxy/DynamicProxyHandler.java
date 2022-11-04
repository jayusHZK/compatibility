package com.jayus.onjava.nineteen.agent.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : h zk
 * @date : 2022/8/5 11:12
 * @description :
 **/
public class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*** proxy:" + proxy.getClass() + ",method:" + method + ",args" + args);
        if (args != null) {
            for (Object item : args) {
                System.out.println(" "+ args);
            }
        }
        return method.invoke(proxied,args);
    }
}
