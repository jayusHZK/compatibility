package com.jayus.onjava.concurrentUnderlying.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : h zk
 * @date : 2022/8/16 18:16
 * @description :
 **/
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread2());
        exec.shutdown();
    }
}
