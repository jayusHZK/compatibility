package com.jayus.onjava.concurrent.CompletableFuture;

import sun.java2d.pipe.SpanIterator;

import java.util.concurrent.CompletableFuture;

import static com.jayus.onjava.concurrent.CompletableFuture.CompletableUtilities.*;

/**
 * @author : h zk
 * @date : 2022/8/15 17:43
 * @description :
 **/
public class CompletableOperations {
    static CompletableFuture<Integer> cfi(int i) {
        return CompletableFuture.completedFuture(Integer.valueOf(i));
    }

    /**
     thenAccept()处理正常结果；

     exceptional()处理异常结果；

     thenApplyAsync()用于串行化另一个CompletableFuture

     anyOf()和allOf()用于并行化多个CompletableFuture
     */
    public static void main(String[] args) {
        //thenAccept()处理正常结果；

        showr(cfi(1));
        voidr(cfi(2).thenRunAsync(() -> {
            System.out.println("thenRunAsync");
        }));
        voidr(CompletableFuture.runAsync(() -> {
            System.out.println("runAsync is static");
        }));
        showr(CompletableFuture.supplyAsync(() -> 99));
        voidr(cfi(4).thenAcceptAsync(i -> System.out.println("thenAcceptAsync: " + i)));
        // thenApplyAsync串行化另一个CompletableFuture
        showr(cfi(5).thenApplyAsync(i -> i + 42));
        showr(cfi(6).thenComposeAsync(i -> cfi(i + 99)));
        CompletableFuture<Integer> c = cfi(7);
        c.obtrudeValue(111);
        showr(c);
        showr(cfi(8).toCompletableFuture());
        c = new CompletableFuture<>();
        c.complete(9);
        showr(c);
        System.out.println(c.isCancelled());
        System.out.println(c.isCompletedExceptionally());
        System.out.println(c.isDone());
        System.out.println(c);
        c = new CompletableFuture<>();
        System.out.println(c.getNow(777));
        c = new CompletableFuture<>();
        c.thenApplyAsync(i -> i + 42).thenApplyAsync(i -> i * 12);
        System.out.println(c.getNumberOfDependents());
        c.thenApplyAsync(i -> i / 2);
        System.out.println(c.getNumberOfDependents());
    }
}
