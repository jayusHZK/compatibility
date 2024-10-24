package com.jayus.asm.source;

import java.util.List;

/**
 * @ClassName MyRunnable
 * @Description: 通过asm处理的类
 * @date: 2024/10/24 15:14
 */
public class MyRunnable implements Runnable {

    String name = "name";

    static String test = "test";

    List<String> names;

    static final String constString = "hello world";

    @Override
    public void run() {

    }

    public int a() {
        if (Math.random() > 0.5) {
            return 1;
        } else if (Math.random() == 0.5) {
            return 0;
        }
        return -1;
    }
}
