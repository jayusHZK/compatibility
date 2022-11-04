package com.jayus.onjava.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : h zk
 * @date : 2022/8/15 19:34
 * @description :
 **/
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> c = new CompletableFuture();
        c.thenApplyAsync(i -> {
            i = i + 9;
            return i;
        });
        System.out.println(c.get());
    }
}
