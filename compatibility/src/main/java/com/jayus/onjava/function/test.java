package com.jayus.onjava.function;

import java.util.function.Function;

/**
 * @author : h zk
 * @date : 2022/7/22 17:34
 * @description :
 **/
public class test {
    static Function<String,String> f1 = s -> {
        System.out.println(s);
        return s + "b";
    },
    f2 = s -> {
        System.out.println(s);
        return s + "c";
    };

    public static void main(String[] args) {
        f1.compose(f2).apply("a");
    }
}
