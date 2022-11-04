package com.jayus.onjava.nineteen.agent.simple;

/**
 * @author : h zk
 * @date : 2022/8/4 17:55
 * @description :
 **/
public class SimpleProxy implements Interface {

    private Interface proxied;

    SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();
    }

    @Override
    public void doSomethingElse(String arg) {
        System.out.println("SimpleProxy doSomethingElse "+arg);
        proxied.doSomethingElse(arg);
    }
}
