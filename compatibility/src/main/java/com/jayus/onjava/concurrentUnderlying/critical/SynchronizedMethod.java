package com.jayus.onjava.concurrentUnderlying.critical;

import com.jayus.onjava.concurrent.createTask.Nap;

/**
 * @author : h zk
 * @date : 2022/8/17 9:40
 * @description :
 **/
public class SynchronizedMethod extends Guarded{
    @Override
    public synchronized void method() {
        new Nap(0.01);
        callCount.incrementAndGet();
    }
}
