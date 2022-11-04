package com.jayus.onjava.nineteen.agent.simple;

/**
 * @author : h zk
 * @date : 2022/8/4 17:54
 * @description :
 **/
public class RealObject implements Interface{
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void doSomethingElse(String arg) {
        System.out.println("doSomethingElse "+arg);
    }
}
