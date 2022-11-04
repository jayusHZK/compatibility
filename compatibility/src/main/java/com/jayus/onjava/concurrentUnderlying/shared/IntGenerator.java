package com.jayus.onjava.concurrentUnderlying.shared;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : h zk
 * @date : 2022/8/16 19:20
 * @description :
 **/
public abstract class IntGenerator {
    private AtomicBoolean canceled = new AtomicBoolean();
    public abstract int next();
    public void cancel(){canceled.set(true);}
    public boolean isCanceled(){
        return canceled.get();
    }
}
