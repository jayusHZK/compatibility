package com.jayus.onjava.function.RunnableMethodReference;

/**
 * @author : h zk
 * @date : 2022/7/19 10:29
 * @description :
 **/
public class RunnableMethodReference {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous");
            }
        }).start();
        new Thread(() -> {
            System.out.println("lambda");
        }).start();
        new Thread(Go::go).start();
    }
}
