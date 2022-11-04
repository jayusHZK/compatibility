package com.jayus.onjava.concurrent.createTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/15 14:07
 * @description :
 **/
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        IntStream.range(0,10)
                .mapToObj(NapTask::new)
                .forEach(exec::execute);
        System.out.println("All tasks submitted");
        exec.shutdown();
        while (!exec.isTerminated()) {
            System.out.println(Thread.currentThread().getName() + " awaiting termination");
            new Nap(0.1);
        }
        Thread.interrupted();

    }
}
