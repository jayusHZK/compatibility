package com.jayus.onjava.stream.StreamOf;

import java.util.Arrays;

/**
 * @author : h zk
 * @date : 2022/7/27 14:28
 * @description :
 **/
public class Machine2 {
    public static void main(String[] args) {
        Arrays.stream(new Bubble[] {
                new Bubble(1),new Bubble(2),new Bubble(3)
        }).forEach(System.out::println);

    }
}
