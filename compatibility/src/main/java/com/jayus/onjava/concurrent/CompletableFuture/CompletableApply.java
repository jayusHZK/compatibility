package com.jayus.onjava.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author : h zk
 * @date : 2022/8/15 17:03
 * @description :
 **/
public class CompletableApply {
    public static void main(String[] args) {
        CompletableFuture<Machina> cf = CompletableFuture.completedFuture(new Machina(0));
        CompletableFuture<Machina> cf2 = cf.thenApply(Machina::work);
        CompletableFuture<Machina> cf3 = cf2.thenApply(Machina::work);
        CompletableFuture<Machina> cf4 = cf3.thenApply(Machina::work);
        CompletableFuture<Machina> cf5 = cf4.thenApply(Machina::work);
    }
}
