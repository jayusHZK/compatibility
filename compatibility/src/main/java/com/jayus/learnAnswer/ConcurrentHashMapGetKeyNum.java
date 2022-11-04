package com.jayus.learnAnswer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/10/26 14:58
 * @description :
 **/
public class ConcurrentHashMapGetKeyNum {

    // 循环次数
    private static int LOOP_COUNT = 10000000;

    // 线程数
    private static int THREAD_COUNT = 10;

    // 元素数量
    private static int ITEM_COUNT = 10;

    public static void main(String[] args) {

    }

    private Map<String,Long> gooduse() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.range(1,LOOP_COUNT).parallel().forEach(i ->{
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freqs.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e-> e.getValue().longValue()
                ));
    }
}
