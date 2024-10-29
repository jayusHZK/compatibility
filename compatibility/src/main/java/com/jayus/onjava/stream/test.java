package com.jayus.onjava.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName test
 * @Description:
 * @date: 2024/10/28 20:02
 */
public class test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("a"+i);
        }
        list.add("a");
        // fork-join
        list.stream().parallel().forEach(i -> {
                    System.out.println(Thread.currentThread().getName());
                }
        );
    }

}
