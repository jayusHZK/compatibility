package com.jayus.ThreadPool;

/**
 * @author : h zk
 * @date : 2022/7/7 16:35
 * @description :
 **/
public class ThreadTest {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e) -> {
            System.out.println("Thread " + t.getName() + " trigger Error Cause: " + e);
        });
        new Thread(() ->{
            int i = 1/0;
        }).run();
    }
}
