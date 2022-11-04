package com.jayus.onjava.concurrentUnderlying;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/16 17:37
 * @description :
 **/
public class ShowThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors()); //获取cpu核数
        ExecutorService exec = Executors.newWorkStealingPool();
        IntStream.range(0,10).mapToObj(n -> new ShowThread()).forEach(exec::execute);
        exec.awaitTermination(1, TimeUnit.SECONDS);
    }
}
