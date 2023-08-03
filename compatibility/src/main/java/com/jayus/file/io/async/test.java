package com.jayus.file.io.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class test {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        Runnable runnable = new Runnable(){
            @Override
            public void run() {

            }
        };

        //Future
        RunnableFuture<String> rf = new RunnableFuture<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return "a";
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return "a";
            }

            @Override
            public void run() {
                System.out.println("runnableFuture 开始执行");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("runnableFuture 执行结束");
            }
        };

        rf.run();
        System.out.println(rf.get(3, TimeUnit.SECONDS));

    }

}
