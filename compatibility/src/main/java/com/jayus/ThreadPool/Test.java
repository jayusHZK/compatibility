package com.jayus.ThreadPool;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : h zk
 * @date : 2022/7/6 14:04
 * @description :
 **/
public class Test {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolCreate.getThreadPoolExecutor();
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            Thread.currentThread().start();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //int a = 1 / 0;
            System.out.println("sleep stop");
        });
        //thread.setUncaughtExceptionHandler(new ThreadErrorHandler());
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdownNow();
        System.out.println("shutdown!");
    }
}
