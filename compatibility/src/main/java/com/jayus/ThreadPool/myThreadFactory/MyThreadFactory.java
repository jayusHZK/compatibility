package com.jayus.ThreadPool.myThreadFactory;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread();
        thread.setName("myThreadPool");
        thread.setUncaughtExceptionHandler((t,e) -> {
            System.out.println("线程:"+t+"发生异常:" + e.getMessage());
        });
        return null;
    }
}
