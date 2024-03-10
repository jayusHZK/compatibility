package com.jayus.ThreadPool;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.*;

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

        TransmittableThreadLocal transmittableThreadLocal = new TransmittableThreadLocal();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Executor ttlExecutor = TtlExecutors.getTtlExecutor(executor);
    }
}
