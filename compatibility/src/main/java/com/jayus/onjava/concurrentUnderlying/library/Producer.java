package com.jayus.onjava.concurrentUnderlying.library;

import com.jayus.onjava.concurrent.createTask.Nap;

import java.util.Queue;
import java.util.SplittableRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : h zk
 * @date : 2022/8/17 14:50
 * @description :
 **/
public class Producer implements Runnable{
    private static AtomicInteger seed = new AtomicInteger(47);
    private SplittableRandom rand = new SplittableRandom(seed.getAndAdd(1));
    private Queue<Prioritized> queue;

    public Producer(Queue<Prioritized> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        rand.ints(10,0,20)
                .mapToObj(Prioritized::new)
                .peek(p -> new Nap(rand.nextDouble() / 10))
                .forEach(p -> queue.add(p));
        queue.add(new Prioritized.EndSentinel());
    }


}
