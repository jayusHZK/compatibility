package com.jayus.ThreadPool.MyThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyThreadPoolTest
 * @Description: 自定义线程池测试
 * @date: 2024/2/29 03:22
 */
public class MyThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor tl = new MyThreadPool(10, 10, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            tl.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            });
        }
        tl.shutdown();
    }

}
