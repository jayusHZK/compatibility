package com.jayus.ThreadPool.MyThreadPool;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyThreadPool
 * @Description: 自定义线程池
 * @date: 2024/2/29 03:21
 */
public class MyThreadPool extends ThreadPoolExecutor {

    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, @NotNull TimeUnit unit, @NotNull BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("before");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("after");
    }

    @Override
    protected void terminated() {
        System.out.println("terminated");
    }
}
