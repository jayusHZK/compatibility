package com.jayus.onjava.nineteen.agent.Proxy;

import com.jayus.onjava.nineteen.agent.simple.Interface;
import com.jayus.onjava.nineteen.agent.simple.RealObject;

import java.lang.reflect.Proxy;

/**
 * @author : h zk
 * @date : 2022/8/5 11:15
 * @description :
 **/
public class SimpleDynamicProxy {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.doSomethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);

        System.out.println(realObject.getClass().getClassLoader());

        Interface proxy = (Interface) Proxy.newProxyInstance(realObject.getClass().getClassLoader(),
                realObject.getClass().getInterfaces(),
                new DynamicProxyHandler(realObject));
        consumer(proxy);
    }
}
