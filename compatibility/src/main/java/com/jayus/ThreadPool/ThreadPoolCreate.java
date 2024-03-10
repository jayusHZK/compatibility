package com.jayus.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : h zk
 * @date : 2022/7/6 13:51
 * @description :
 **/
public class ThreadPoolCreate {

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(4, 16, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            int a = 1 / 0;
        });
        //thread.setUncaughtExceptionHandler(new ThreadErrorHandler());
        thread.start();

    }


}
