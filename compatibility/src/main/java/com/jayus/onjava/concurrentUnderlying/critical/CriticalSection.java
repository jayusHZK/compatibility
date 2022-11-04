package com.jayus.onjava.concurrentUnderlying.critical;

import com.jayus.onjava.concurrent.createTask.Nap;

/**
 * @author : h zk
 * @date : 2022/8/17 9:41
 * @description :
 **/
public class CriticalSection extends Guarded{
    @Override
    public void method() {
        new Nap(0.01);
        synchronized (this) {
            callCount.incrementAndGet();
        }
    }
}
