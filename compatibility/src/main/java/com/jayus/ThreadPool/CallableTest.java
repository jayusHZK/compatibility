package com.jayus.ThreadPool;

import java.util.concurrent.Callable;

/**
 * @author : h zk
 * @date : 2022/7/7 16:17
 * @description :
 **/
public class CallableTest {
    public static void main(String[] args) throws Exception {
        Callable<String> callable =  new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call return";
            }
        };
        String call = callable.call();
        System.out.println(call);

    }
}
