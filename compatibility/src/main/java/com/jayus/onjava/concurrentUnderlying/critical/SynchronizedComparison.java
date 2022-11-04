package com.jayus.onjava.concurrentUnderlying.critical;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/17 9:47
 * @description :
 **/
public class SynchronizedComparison {
    static void test(Guarded g) {
        List<CompletableFuture> callers = Stream.of(
                new Caller(g),
                new Caller(g),
                new Caller(g),
                new Caller(g))
                .map(CompletableFuture::runAsync)
                .collect(Collectors.toList());
        callers.forEach(CompletableFuture::join);
        System.out.println(g);
    }

    public static void main(String[] args) {
        test(new CriticalSection());
        test(new SynchronizedMethod());
    }
}
