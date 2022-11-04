package com.jayus.onjava.nineteen.agent.simple;

/**
 * @author : h zk
 * @date : 2022/8/5 10:47
 * @description :
 **/
public class SimpleProxyDemo {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.doSomethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
