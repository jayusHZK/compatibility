package com.jayus.onjava.concurrentUnderlying.shared;

import org.w3c.dom.ranges.Range;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/16 19:21
 * @description :
 **/
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator gp,int count){
        List<CompletableFuture> checkers = IntStream.range(0, count)
                .mapToObj(i -> new EvenChecker(gp, i))
                .map(CompletableFuture::runAsync)
                .collect(Collectors.toList());
        checkers.forEach(CompletableFuture::join);
    }

    public static void test(IntGenerator gp){
        test(gp,10);
    }

}
