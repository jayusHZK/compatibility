package com.jayus.onjava.concurrent.CompletableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author : h zk
 * @date : 2022/8/15 17:18
 * @description :
 **/
public class CompletableApplyAsync {
    public static void main(String[] args) {
        CompletableFuture<Machina> cf = CompletableFuture.completedFuture(new Machina(0))
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work);
        System.out.println(cf.join());
    }
}
