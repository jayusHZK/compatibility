package com.jayus.ThreadPool;

/**
 * @author : h zk
 * @date : 2022/7/6 14:12
 * @description :
 **/
public class ThreadErrorHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
    }
}
