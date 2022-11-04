package com.jayus.onjava.concurrentUnderlying.library;

import com.jayus.onjava.concurrent.createTask.Nap;

import java.util.SplittableRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author : h zk
 * @date : 2022/8/17 14:57
 * @description :
 **/
public class Consumer implements Runnable{
    private PriorityBlockingQueue<Prioritized> q;
    private SplittableRandom rand = new SplittableRandom(47);

    public Consumer(PriorityBlockingQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Prioritized pt = q.take();
                System.out.println(pt);
                if (pt instanceof Prioritized.EndSentinel) {
                    pt.displaySequence();
                    break;
                }
                new Nap(rand.nextDouble() / 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue queue = new PriorityBlockingQueue();
        CompletableFuture.runAsync(new Producer(queue));
        CompletableFuture.runAsync(new Producer(queue));
        CompletableFuture.runAsync(new Producer(queue));
        CompletableFuture.runAsync(new Consumer(queue)).join();

    }
}
