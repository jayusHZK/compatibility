package com.jayus.onjava.concurrent.endTask;

import com.jayus.onjava.concurrent.createTask.Nap;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : h zk
 * @date : 2022/8/15 16:23
 * @description :
 **/
public class QuittingCompletable {
    public static void main(String[] args) {
        List<QuittableTask> tasks = IntStream.range(1, QuittingTasks.COUNT)
                .mapToObj(QuittableTask::new)
                .collect(Collectors.toList());
        List<CompletableFuture> cfutures = tasks.stream().map(CompletableFuture::runAsync)
                .collect(Collectors.toList());
        new Nap(1);
        tasks.forEach(QuittableTask::quit);
        cfutures.forEach(CompletableFuture::join);
    }
}
