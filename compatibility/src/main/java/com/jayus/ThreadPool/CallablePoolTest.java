package com.jayus.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : h zk
 * @date : 2022/7/7 16:20
 * @description :
 **/
public class CallablePoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor tpe = ThreadPoolCreate.getThreadPoolExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call return";
            }
        };
        Future<String> submit = tpe.submit(callable);
        System.out.println(submit.get());
        tpe.shutdown();
    }

}
