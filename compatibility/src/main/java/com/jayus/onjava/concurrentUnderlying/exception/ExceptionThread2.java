package com.jayus.onjava.concurrentUnderlying.exception;

/**
 * @author : h zk
 * @date : 2022/8/16 18:01
 * @description :
 **/
public class ExceptionThread2 implements Runnable{
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run(0 by " + t.getName());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }


}
