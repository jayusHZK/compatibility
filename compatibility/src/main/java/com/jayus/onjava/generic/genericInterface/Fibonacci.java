package com.jayus.onjava.generic.genericInterface;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/9 11:40
 * @description :
 **/
public class Fibonacci implements Supplier<Integer> {

    private int count = 0;

    private static int a = 0;

    @Override
    public Integer get() {
        System.out.println(count);
        return fib(count++);
    }

    private int fib(int n){
        if (n < 2) return 1;
        return fib(n-2) + fib(n -1);
    }

    public static void main(String[] args) {
        Stream.generate(new Fibonacci())
                .limit(18)
                .map(n -> n + " ")
                .forEach(System.out::print);
    }
}
