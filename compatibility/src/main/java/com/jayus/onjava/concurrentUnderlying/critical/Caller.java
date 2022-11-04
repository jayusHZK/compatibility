package com.jayus.onjava.concurrentUnderlying.critical;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : h zk
 * @date : 2022/8/17 9:42
 * @description :
 **/
public class Caller implements Runnable{

    private Guarded g;

    public Caller(Guarded g) {
        this.g = g;
    }

    private AtomicLong successfulCalls = new AtomicLong();

    private AtomicBoolean stop = new AtomicBoolean();

    @Override
    public void run() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                stop.set(true);
            }
        },2500);
        while (!stop.get()) {
            g.method();
            successfulCalls.getAndIncrement();
        }
        System.out.println("-> " + successfulCalls.get());
    }
}
