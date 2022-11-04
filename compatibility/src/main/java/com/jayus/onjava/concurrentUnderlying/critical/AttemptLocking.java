package com.jayus.onjava.concurrentUnderlying.critical;

import com.jayus.onjava.concurrent.createTask.Nap;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : h zk
 * @date : 2022/8/17 10:31
 * @description :
 **/
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed(){
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();
        CompletableFuture.runAsync(()-> {
            al.lock.lock();
            System.out.println("acquired");
        });
        new Nap(0.1);
        al.untimed();
        al.timed();
    }
}
