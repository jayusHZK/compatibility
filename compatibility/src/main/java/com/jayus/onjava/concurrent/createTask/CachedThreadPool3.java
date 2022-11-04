package com.jayus.onjava.concurrent.createTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/15 15:32
 * @description :
 **/
public class CachedThreadPool3 {
    public static Integer extractResult(Future<Integer> f){
        try {
            return f.get();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<CountingTask> list = IntStream.range(0,10).mapToObj(CountingTask::new)
                .collect(Collectors.toList());
        List<Future<Integer>> futures = exec.invokeAll(list);
        Integer reduce = futures.stream().map(CachedThreadPool3::extractResult).reduce(0, Integer::sum);
        System.out.println(reduce);
        exec.shutdown();
    }
}
