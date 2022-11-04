package com.jayus.onjava.concurrent.endTask;

import com.jayus.onjava.concurrent.createTask.Nap;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/15 16:19
 * @description :
 **/
public class QuittingTasks {
    public static final int COUNT = 150;

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<QuittableTask> tasks = IntStream.range(1, COUNT)
                .mapToObj(QuittableTask::new)
                .peek(qt -> es.execute(qt))
                .collect(Collectors.toList());
        new Nap(1);
        tasks.forEach(QuittableTask::quit);
        es.shutdown();
    }
}
