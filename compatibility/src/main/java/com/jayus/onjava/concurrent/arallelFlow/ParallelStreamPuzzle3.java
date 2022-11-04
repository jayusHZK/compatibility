package com.jayus.onjava.concurrent.arallelFlow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/15 11:10
 * @description :
 **/
public class ParallelStreamPuzzle3 {
    public static void main(String[] args) {
        List<Integer> x = IntStream.range(0, 30)
                .peek(e -> System.out.println(e + ": " + Thread.currentThread().getName()))
                .limit(10)
                .parallel()
                .boxed()
                .collect(Collectors.toList());
        System.out.println(x);
    }
}
