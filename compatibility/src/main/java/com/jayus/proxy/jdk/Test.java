package com.jayus.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        People a = new Docker();
        People docker = (People) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                , new Class[]{People.class},new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("工作是");
                Object invoke = method.invoke(a, args);
                System.out.println("ok,很棒");
                return invoke;
            }
        });
        docker.doWhat();
    }

}
