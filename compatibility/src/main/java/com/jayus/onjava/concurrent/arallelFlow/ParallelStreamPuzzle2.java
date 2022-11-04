package com.jayus.onjava.concurrent.arallelFlow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/15 10:43
 * @description :
 **/
public class ParallelStreamPuzzle2 {
    public static final Deque<String> TRACE = new ConcurrentLinkedDeque<>();
    static class IntGenerator implements Supplier<Integer>{
        private AtomicInteger current = new AtomicInteger();
        @Override
        public Integer get() {
            TRACE.add(current.get() +": " + Thread.currentThread().getName());
            return current.getAndIncrement();
        }
    }

    public static void main(String[] args) throws IOException {
        List<Integer> x = Stream.generate(new IntGenerator())
                .limit(10)
                .parallel()
                .collect(Collectors.toList());
        System.out.println(x);
        Files.write(Paths.get("PSP2.txt"),TRACE);
    }
}
