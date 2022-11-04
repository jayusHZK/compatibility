package com.jayus.onjava.concurrentUnderlying.exception;

/**
 * @author : h zk
 * @date : 2022/8/16 18:09
 * @description :
 **/
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}
