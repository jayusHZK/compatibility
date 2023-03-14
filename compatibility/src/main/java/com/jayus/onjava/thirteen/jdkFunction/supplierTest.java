package com.jayus.onjava.thirteen.jdkFunction;

import java.util.function.Supplier;

/**
 * @author : h zk
 * @date : 2023/3/14 11:27
 * @description : 传入参数进行处理
 **/
public class supplierTest {
    public static void main(String[] args) {
        String a = "abc";
        System.out.println(get(() -> a));
        System.out.println(get(() -> "name", () -> "qiumin"));
    }

    public static String get(Supplier<String> supplier) {
        String a = supplier.get().split("b")[0];
        return a;
    }

    public static String get(Supplier<String> supplier, Supplier<String> supplier1) {
        return supplier.get() + ":" + supplier1.get();
    }


}

