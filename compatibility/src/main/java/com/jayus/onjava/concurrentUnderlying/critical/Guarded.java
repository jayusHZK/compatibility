package com.jayus.onjava.concurrentUnderlying.critical;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : h zk
 * @date : 2022/8/17 9:39
 * @description :
 **/
public abstract class Guarded {
    AtomicLong callCount = new AtomicLong();
    public abstract void method();

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + callCount.get();
    }
}
